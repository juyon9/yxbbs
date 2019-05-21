package com.yx.bbs.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringUtil {

	public static final String SESSION_USER = "sesionUser";

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

}
