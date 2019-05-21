package com.yx.bbs.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yx.bbs.entity.Module;

public interface ModuleRepository extends PagingAndSortingRepository<Module, Long> {

}
