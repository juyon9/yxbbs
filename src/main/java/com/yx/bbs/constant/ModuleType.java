package com.yx.bbs.constant;

public enum ModuleType {
	后端开发(1), 前端开发(2), 移动端(3), 数据库(4), 第三方应用(5), 实用工具(6), 其他(7), 公告(8);

	private long type;

	private ModuleType(long type) {
		this.type = type;
	}

	public long getType() {
		return type;
	}

}
