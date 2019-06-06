package com.yx.bbs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.bbs.dao.PostsSortRepository;
import com.yx.bbs.entity.PostsSort;

@Service("postsSortServiceImpl")
public class PostsSortServiceImpl implements PostsSortService {

	private Map<Long, String> sortMap = new HashMap<Long, String>();;

	@Autowired
	private PostsSortRepository sortRepo;

	@Override
	public String findNameById(long id) {
		String name = sortMap.get(id);
		if (name == null) {
			Optional<PostsSort> opt = sortRepo.findById(id);
			if (opt.isPresent()) {
				PostsSort module = opt.get();
				name = module.getName();
				sortMap.put(id, name);
			}
		}
		return name == null ? "未知" : name;
	}

}
