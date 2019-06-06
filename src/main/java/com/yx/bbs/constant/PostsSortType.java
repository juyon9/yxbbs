package com.yx.bbs.constant;

public enum PostsSortType {

	提问(1), 分享(2), 讨论(3), 建议(4), 公告(5);

	private long type;

	private PostsSortType(long type) {
		this.type = type;
	}

	public long getType() {
		return type;
	}

}
