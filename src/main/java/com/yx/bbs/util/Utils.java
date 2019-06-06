package com.yx.bbs.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import com.yx.bbs.vo.SessionUser;

/**
 * 工具类
 * 
 * @author juyon
 *
 */
public class Utils {

	/**
	 * 获取会话信息
	 * 
	 * @param sess 会话
	 * @return 会话信息
	 */
	public static SessionUser sessionUser(HttpSession sess) {
		return (SessionUser) sess.getAttribute(StringUtil.SESSION_USER);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
