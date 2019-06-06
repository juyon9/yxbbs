package com.yx.bbs;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.yx.bbs.dao.UserRepository;
import com.yx.bbs.entity.User;
import com.yx.bbs.service.UserService;
import com.yx.bbs.util.StringUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	private static final Logger LOG = LoggerFactory.getLogger(UserTests.class);

	@Autowired
	private UserRepository repo;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Test
	public void saveUsers() {
		List<User> list = new LinkedList<User>();
		for (int i = 1; i < 10; i++) {
			list.add(new User("Juyon0" + i + "@163.com", StringUtil.encodeStr("123456"), "Juyon0" + i, "y",
					new Timestamp(System.currentTimeMillis() + i * 2000)));
		}
		repo.saveAll(list);
	}

	@Test
	public void updatePassword() {
		User user = new User("1007719768@qq.com", "654321", null, null, null);
		userService.updatePasswd(user);
	}

	@Test
	public void userSaveAndDel() {
		User user = new User("Juyon12@163.com", StringUtil.encodeStr("123456"), "Juyon12", "y",
				new Timestamp(System.currentTimeMillis()));
		User savedUser = repo.save(user);
		long savedUserId = savedUser.getId();
		LOG.warn("Juyon->新增用户,id为: " + savedUserId);
		repo.deleteById(savedUserId);
	}

	@Test
	public void login() {
		User u = repo.queryByUsernameAndPassword("juyon01@163.com", StringUtil.encodeStr("1234567"));
		if (u != null) {
			LOG.warn(u.toString());
		} else {
			LOG.warn("用户不存在...");
		}
	}

	@Test
	public void existsUsername() {
		User u = repo.existsByUsername("admin2015@cji.com ");
		if (u != null) {
			LOG.warn(u.toString());
		} else {
			LOG.warn("用户不存在...");
		}
	}

	@Test
	public void page() {
		// 分页查询
		Page<User> pageAll = repo.findAll(PageRequest.of(0, 3, Direction.DESC, "registTime"));
		List<User> content = pageAll.getContent();
		String pageInfo = String.format("Juyon->页码:%d, 条数:%d, 总条数:%s", pageAll.getNumber() + 1, content.size(),
				pageAll.getTotalElements());
		LOG.warn(pageInfo);
		LOG.warn("Juyon->数据：");
		int i = 1;
		for (User u : content) {
			LOG.warn("\t" + (i++) + ". " + u);
		}

	}

}
