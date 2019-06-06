package com.yx.bbs;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yx.bbs.util.StringUtil;

public class SessionInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(SessionInterceptor.class);
	private static byte[] noAuth;
	private static Set<String> noAuthzUrl;

	static {
		noAuth = "{\"code\":5,\"msg\":\"您无权访问\"}".getBytes();
		noAuthzUrl = new HashSet<String>();
		noAuthzUrl.add("/yxbbsApi/user/login");
		noAuthzUrl.add("/yxbbsApi/user/register");
		noAuthzUrl.add("/yxbbsApi/user/sendCode");
		noAuthzUrl.add("/yxbbsApi/user/imgCode");
		noAuthzUrl.add("/yxbbsApi/user/passwd");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp, Object handler) throws Exception {
		HandlerInterceptor.super.preHandle(req, rsp, handler);
		String requestURI = req.getRequestURI();
		LOG.warn("Juyon->请求:" + requestURI);
		// 登录请求，或者用户已登录，则直接跳转
		boolean flag = true;
		if (!noAuthzUrl.contains(requestURI) && req.getSession().getAttribute(StringUtil.SESSION_USER) == null) {
			rsp.setHeader("Content-Type", "application/json");
			rsp.setHeader("Content-Length", String.valueOf(noAuth.length));
			rsp.getOutputStream().write(noAuth);
			flag = false;
		}
		return flag;
	}

}
