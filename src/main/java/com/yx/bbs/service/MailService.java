package com.yx.bbs.service;

import org.springframework.mail.MailException;

public interface MailService {

	/**
	 * 发送6位数字验证码
	 * 
	 * @param to 接收者邮箱
	 * @return
	 * @throws MailException
	 */
	public String sendCodeMail(String to) throws MailException;
}
