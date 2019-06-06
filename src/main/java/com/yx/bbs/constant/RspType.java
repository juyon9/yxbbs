package com.yx.bbs.constant;

public enum RspType {

	成功(0), 操作失败(1), 未知错误(2), 帐号或密码错误(3), 账户已冻结(4), 无权访问(5);

	private int code;

	private RspType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
