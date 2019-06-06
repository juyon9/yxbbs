package com.yx.bbs.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * 字符串实用类
 * 
 * @author juyon
 *
 */
public class StringUtil {

	public static final String SESSION_USER = "sesionUser";
	public static final String SESSION_REGISTER_CODE = "registerCode";
	public static final String SESSION_LOGIN_CODE = "loginCode";

	public static boolean checkChinese(String chinese) {
		return checkRegEx(chinese, "^[\\u4e00-\\u9fa5]{0,10}");
	}

	/**
	 * 验证电子邮箱，长度小于32位
	 * 
	 * @param email 邮箱地址
	 * @return
	 */
	public static boolean checkEmail(String email) {
		return checkRegEx(email, "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
	}

	/**
	 * 验证密码格式 : 大小写字母数字及下划线，长度大于6位，小于20位
	 * 
	 * @param pwd 密码
	 * @return
	 */
	public static boolean checkPassword(String pwd) {
		return checkRegEx(pwd, "[a-zA-Z_0-9_]{6,20}");
	}

	/**
	 * 
	 * 生成加密串
	 * 
	 * @param str 加密前
	 * @return 加密后
	 */
	public static String encodeStr(String str) {
		String encodeStr = null;
		if (str != null) {
			try {
				encodeStr = Base64.getEncoder()
						.encodeToString(MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8")));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return encodeStr;
	}

	/**
	 * 正则表达式匹配
	 * 
	 * @param str   匹配字符串
	 * @param regEx 正则表达式
	 * @return
	 */
	private static boolean checkRegEx(String str, String regEx) {
		boolean flag = false;
		if (str != null && regEx != null) {
			flag = str.matches(regEx);
		}
		return flag;
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
