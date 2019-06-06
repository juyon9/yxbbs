package com.yx.bbs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yx.bbs.dao.ReplyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyTests {

	@Autowired
	private ReplyRepository repo;

	@Test
	public void queryCount() {
		System.out.println(repo.countByPosts(5l));
	}

}
