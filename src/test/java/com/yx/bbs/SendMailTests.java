package com.yx.bbs;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yx.bbs.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMailTests {

	@Resource(name = "mailServiceImpl")
	private MailService mailService;

	@Test
	public void sendMail() {
		mailService.sendCodeMail("1007719768@qq.com");
	}

}
