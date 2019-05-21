package com.yx.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yx.bbs.entity.User;
import com.yx.bbs.util.StringUtil;

public class SessionInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp, Object handler) throws Exception {
		LOG.warn("Juyon->请求:" + req.getRequestURI());
		User user = (User) req.getSession().getAttribute(StringUtil.SESSION_USER);
		if (user == null) {
			LOG.warn("Juyon->用户未登录.");
		} else {
			LOG.warn("Juyon->登录账号: " + user.toString());
		}
		return HandlerInterceptor.super.preHandle(req, rsp, handler);
	}

}
