package com.yx.bbs;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.yx.bbs.dao.UserRepository;
import com.yx.bbs.entity.User;
import com.yx.bbs.util.StringUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Autowired
	private UserRepository repo;

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
	public void login() {
		User u = repo.queryByUsernameAndPassword("juyon01@163.com", StringUtil.encodeStr("1234567"));
		if (u != null) {
			System.out.println(u);
		} else {
			System.out.println("用户不存在...");
		}
	}

	@Test
	public void page() {
		// 统计数量
		System.out.println("Juyon->count:" + repo.count());

		// 按id查询
		System.out.println("Juyon->existsById:" + repo.existsById(1l));

		// 对象包装，避免空指针
		Optional<User> opt = repo.findById(2l);
		if (opt.isPresent()) {
			User user = opt.get();
			System.out.println(user);
		} else {
			System.out.println("未查询到对象.");
		}

		// 查询所有
		Iterable<User> all = repo.findAll();
		all.forEach((u) -> {
			System.out.println(u);
		});

		// 分页查询
		Page<User> pageAll = repo.findAll(PageRequest.of(0, 3, Direction.DESC, "registTime"));
		System.out.println("Juyon->getTotalElements=" + pageAll.getTotalElements());
		System.out.println("Juyon->getTotalPages=" + pageAll.getTotalPages());
		System.out.println("Juyon->getSize=" + pageAll.getSize());
		System.out.println("Juyon->getNumber=" + pageAll.getNumber());
		List<User> content = pageAll.getContent();
		System.out.println("Juyon-> content.size = " + content.size());
		content.forEach((u) -> {
			System.out.println("Juyon-> u = " + u);
		});

	}

}
