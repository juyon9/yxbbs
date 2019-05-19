package com.yx.bbs.constant;

public enum UserStatus {

	有效("y"), 冻结("n");

	private String status;

	private UserStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
