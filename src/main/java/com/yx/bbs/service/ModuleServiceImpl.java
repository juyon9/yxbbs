package com.yx.bbs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yx.bbs.dao.ModuleRepository;
import com.yx.bbs.entity.Module;

@Service("moduleServiceImpl")
public class ModuleServiceImpl implements ModuleService {

	private Map<Long, String> moduleMap = new HashMap<Long, String>();

	@Autowired
	private ModuleRepository moduleRepo;

	@Override
	public String findNameById(long id) {
		String name = moduleMap.get(id);
		if (name == null) {
			Optional<Module> opt = moduleRepo.findById(id);
			if (opt.isPresent()) {
				Module module = opt.get();
				name = module.getName();
				moduleMap.put(id, name);
			}
		}
		return name == null ? "未知" : name;
	}

}
