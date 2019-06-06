package com.yx.bbs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yx.bbs.constant.RspType;
import com.yx.bbs.constant.UserStatus;
import com.yx.bbs.entity.User;
import com.yx.bbs.service.MailService;
import com.yx.bbs.service.UserService;
import com.yx.bbs.util.ImageUtil;
import com.yx.bbs.util.StringUtil;
import com.yx.bbs.util.Utils;
import com.yx.bbs.vo.Rsp;
import com.yx.bbs.vo.RspData;
import com.yx.bbs.vo.SessionUser;

@RestController
@RequestMapping("/user")
public class UserController {

	private Map<String, Long> emailRecord = new HashMap<String, Long>();

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "mailServiceImpl")
	private MailService mailService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RspData<SessionUser> login(String uname, String pwd, String code, HttpSession sess) {
		RspData<SessionUser> rsp = null;
		if (code != null && !code.equalsIgnoreCase((String) sess.getAttribute(StringUtil.SESSION_LOGIN_CODE))) {
			rsp = new RspData<SessionUser>("验证码错误");
		}
		if (rsp == null) {
			SessionUser u = userService.checkLogin(uname, pwd);
			if (u == null) {
				rsp = new RspData<SessionUser>(RspType.帐号或密码错误);
			} else if (UserStatus.有效.getStatus().equals(u.getStatus())) {
				rsp = new RspData<SessionUser>(RspType.成功);
				rsp.setData(u);
				sess.setAttribute(StringUtil.SESSION_USER, u);
			} else if (UserStatus.冻结.getStatus().equals(u.getStatus())) {
				rsp = new RspData<SessionUser>(RspType.账户已冻结);
			} else {
				rsp = new RspData<SessionUser>(RspType.未知错误);
			}
		}
		return rsp;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Rsp logout(HttpSession sess) {
		sess.removeAttribute(StringUtil.SESSION_USER);
		sess.invalidate();
		return Rsp.instance(RspType.成功);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Rsp register(User user, String code, HttpSession sess) {
		Rsp rsp = null;
		if (code == null || code.length() != 6) {
			rsp = Rsp.instance("验证码错误");
		}
		if (rsp == null && userService.userNameIsValid(user.getUserName())) {
			String[] sessCode = (String[]) sess.getAttribute(StringUtil.SESSION_REGISTER_CODE);
			String userName = user.getUserName();
			if (sessCode == null) {
				rsp = Rsp.instance("验证码不存在");
			} else if (!sessCode[0].equals(userName) || !sessCode[1].equals(code)) {
				rsp = Rsp.instance("验证码错误");
			} else {
				Long lastSent = emailRecord.get(userName);
				// 检查验证码是否过期
				if (lastSent == null || System.currentTimeMillis() - lastSent.longValue() > 300000) {
					rsp = Rsp.instance("验证码已过期");
				}
			}
		}
		if (rsp == null && !StringUtil.checkPassword(user.getPassword())) {
			rsp = Rsp.instance("密码格式错误,只能由字母、数字、下划线组成");
		}
		if (rsp == null && !StringUtil.checkChinese(user.getName())) {
			rsp = Rsp.instance("姓名输入错误,只能是汉字.");
		}
		if (rsp == null && !userService.register(user)) {
			rsp = Rsp.instance(RspType.操作失败);
		}
		if (rsp == null) {
			rsp = Rsp.instance(RspType.成功);
		}
		return rsp;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Rsp passwd(User user, HttpSession sess) {
		Rsp rsp = Rsp.instance(RspType.操作失败);
		if (user != null && StringUtil.checkChinese(user.getName())) {
			user.setId(Utils.sessionUser(sess).getId());
			SessionUser u = userService.updateName(user);
			if (u != null) {
				rsp = Rsp.instance(RspType.成功);
				sess.setAttribute(StringUtil.SESSION_USER, u);
			}
		}
		return rsp;
	}

	@RequestMapping(value = "/myinfo", method = RequestMethod.POST)
	public RspData<SessionUser> myinfo(HttpSession sess) {
		RspData<SessionUser> rsp = new RspData<SessionUser>(RspType.成功);
		rsp.setData(Utils.sessionUser(sess));
		return rsp;
	}

	@RequestMapping(value = "/passwd", method = RequestMethod.POST)
	public Rsp passwd(User user, String code, HttpSession sess) {
		Rsp rsp = null;
		if (code == null || code.length() != 6) {
			rsp = Rsp.instance("验证码错误");
		}
		if (rsp == null && !userService.userNameIsValid(user.getUserName())) {
			String[] sessCode = (String[]) sess.getAttribute(StringUtil.SESSION_REGISTER_CODE);
			String userName = user.getUserName();
			if (sessCode == null) {
				rsp = Rsp.instance("验证码不存在");
			} else if (!sessCode[0].equals(userName) || !sessCode[1].equals(code)) {
				rsp = Rsp.instance("验证码错误");
			} else {
				Long lastSent = emailRecord.get(userName);
				// 检查验证码是否过期
				if (lastSent == null || System.currentTimeMillis() - lastSent.longValue() > 300000) {
					rsp = Rsp.instance("验证码已过期");
				}
			}
		}
		if (rsp == null && !StringUtil.checkPassword(user.getPassword())) {
			rsp = Rsp.instance("密码格式错误,只能由字母、数字、下划线组成");
		}
		if (rsp == null && !userService.updatePasswd(user)) {
			rsp = Rsp.instance(RspType.操作失败);
		}
		if (rsp == null) {
			rsp = Rsp.instance(RspType.成功);
		}
		return rsp;
	}

	@RequestMapping(value = "/sendCode", method = RequestMethod.POST)
	public Rsp sendCode(String toMail, HttpSession sess) {
		Rsp rsp = Rsp.instance(RspType.操作失败);
		Long lastSent = emailRecord.get(toMail);
		long interval = (System.currentTimeMillis() - (lastSent == null ? 0l : lastSent.longValue())) / 1000;

		if (interval < 300) {
			rsp = Rsp.instance(String.format("请于%d秒后再请求发送验证码", (300 - interval)));
		} else if (StringUtil.checkEmail(toMail)) {
			String code = mailService.sendCodeMail(toMail);
			if (code != null && code.length() == 6) {
				sess.setAttribute(StringUtil.SESSION_REGISTER_CODE, new String[] { toMail, code });
				rsp = Rsp.instance("邮件已发送，请注意查收.");
				emailRecord.put(toMail, new Long(System.currentTimeMillis()));
			}
		}
		return rsp;
	}

	@RequestMapping(value = "/imgCode", method = RequestMethod.GET)
	public void imgCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setContentType("image/jpeg");
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ImageUtil imageUtil = new ImageUtil(160, 28, 5, 30);
		session.setAttribute(StringUtil.SESSION_LOGIN_CODE, imageUtil.getCode());
		try {
			imageUtil.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
