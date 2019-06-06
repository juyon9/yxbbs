package com.yx.bbs.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.bbs.constant.UserStatus;
import com.yx.bbs.dao.UserRepository;
import com.yx.bbs.entity.User;
import com.yx.bbs.util.StringUtil;
import com.yx.bbs.vo.SessionUser;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	private Map<Long, String> userMap = new HashMap<Long, String>();

	@Override
	public SessionUser checkLogin(String userName, String pwd) {
		SessionUser sessionUser = null;
		if (StringUtil.checkEmail(userName) && StringUtil.checkPassword(pwd)) {
			User u = userRepo.queryByUsernameAndPassword(userName, StringUtil.encodeStr(pwd));
			if (u != null) {
				sessionUser = new SessionUser(u);
			}
		}
		return sessionUser;

	}

	@Override
	public boolean register(User user) {
		user.setRegistTime(new Timestamp(System.currentTimeMillis()));
		user.setStatus(UserStatus.有效.getStatus());
		String password = user.getPassword();
		user.setPassword(StringUtil.encodeStr(password));
		User savedUser = userRepo.save(user);
		return savedUser.getId() > 0 ? true : false;
	}

	@Override
	public boolean userNameIsValid(String userName) {
		return userRepo.existsByUsername(userName) == null ? true : false;
	}

	@Override
	public boolean updatePasswd(User user) {
		User u = userRepo.queryByUsernameAndStatus(user.getUserName(), UserStatus.有效.getStatus());
		User save = null;
		String newpwd = StringUtil.encodeStr(user.getPassword());
		if (u != null) {
			u.setPassword(newpwd);
			save = userRepo.save(u);
		}
		return save != null && save.getPassword() != newpwd;
	}

	@Override
	public SessionUser updateName(User user) {
		long userId = user.getId();
		SessionUser suser = null;
		if (userId > 0) {
			Optional<User> curr = userRepo.findById(userId);
			if (curr.isPresent()) {
				User u = curr.get();
				String name = user.getName();
				if (!name.equals(u.getName())) {
					u.setName(name);
					userRepo.save(u);
					suser = new SessionUser(u);
				}
			}
		}
		return suser;
	}

	@Override
	public String findNameById(long id) {
		String name = userMap.get(id);
		if (name == null) {
			Optional<User> opt = userRepo.findById(id);
			if (opt.isPresent()) {
				User module = opt.get();
				name = module.getName();
				userMap.put(id, name);
			}
		}
		return name == null ? "未知" : name;
	}
}
