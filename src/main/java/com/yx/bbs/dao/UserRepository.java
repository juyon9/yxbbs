package com.yx.bbs.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yx.bbs.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
