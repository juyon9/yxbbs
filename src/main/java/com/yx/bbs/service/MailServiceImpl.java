package com.yx.bbs.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailServiceImpl")
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	public String sendCodeMail(String to) throws MailException {
		int randomNum = (int) (new Random().nextFloat() * 100000);
		if (randomNum < 100000) {
			randomNum += 100000;
		}
		try {
			sendSimpleMail(to, "[芸享社区-技术论坛] 操作验证码", "您的验证码是: " + randomNum + " (5分钟内有效)");
		} catch (Exception e) {
			e.printStackTrace();
			randomNum = 0;
		}
		return String.valueOf(randomNum);
	}

	private void sendSimpleMail(String to, String subject, String content) throws MailException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from); // 邮件发送者
		message.setTo(to); // 邮件接受者
		message.setSubject(subject); // 主题
		message.setText(content); // 内容
		mailSender.send(message);
	}

}
