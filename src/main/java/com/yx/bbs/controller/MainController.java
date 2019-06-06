package com.yx.bbs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yx.bbs.constant.RspType;
import com.yx.bbs.vo.Rsp;

@RestController
public class MainController {

	@RequestMapping(value = "/authFail")
	public Rsp mainPage() {
		return Rsp.instance(RspType.无权访问);
	}
}
