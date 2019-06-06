package com.yx.bbs.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yx.bbs.entity.PostsSort;

public interface PostsSortRepository extends PagingAndSortingRepository<PostsSort, Long> {

}