package com.yx.bbs.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yx.bbs.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	/**
	 * 按照用户名和密码查询用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@Query("select u from User u where u.userName=?1 and u.password=?2")
	User queryByUsernameAndPassword(String userName, String password);

}
