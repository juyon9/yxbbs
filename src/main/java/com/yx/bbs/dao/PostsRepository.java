package com.yx.bbs.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yx.bbs.entity.Posts;

public interface PostsRepository extends PagingAndSortingRepository<Posts, Long>, JpaSpecificationExecutor<Posts> {

}