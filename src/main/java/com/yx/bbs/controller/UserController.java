package com.yx.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yx.bbs.constant.UserStatus;
import com.yx.bbs.dao.UserRepository;
import com.yx.bbs.entity.User;
import com.yx.bbs.util.StringUtil;
import com.yx.bbs.vo.Rsp;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Rsp login(String uname, String pwd, HttpSession sess) {
		Rsp rsp = null;
		if (uname != null && pwd != null) {
			User u = userRepo.queryByUsernameAndPassword(uname, StringUtil.encodeStr(pwd));
			if (u == null) {
				rsp = Rsp.instance(Rsp.type.帐号或密码错误);
			} else if (UserStatus.有效.getStatus().equals(u.getStatus())) {
				rsp = Rsp.instance(Rsp.type.成功);
				sess.setAttribute("sesionUser", u);
			} else if (UserStatus.冻结.getStatus().equals(u.getStatus())) {
				rsp = Rsp.instance(Rsp.type.账户已冻结);
			} else {
				rsp = Rsp.instance(Rsp.type.未知错误);
			}
		} else {
			rsp = Rsp.instance(Rsp.type.帐号或密码错误);
		}
		return rsp;
	}
}
