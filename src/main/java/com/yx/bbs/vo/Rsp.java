package com.yx.bbs.vo;

import com.yx.bbs.constant.RspType;

public class Rsp {

	// 0表示成功
	private int code;
	// 提示信息
	private String msg;

	/**
	 * 已知错误
	 * 
	 * @param rspType 错误类型枚举
	 * @return
	 */
	public static Rsp instance(RspType rspType) {
		return new Rsp(rspType.getCode(), rspType.name());
	}

	/**
	 * 未知错误
	 * 
	 * @param msg 错误信息
	 * @return
	 */
	public static Rsp instance(String msg) {
		return new Rsp(-1, msg);
	}

	private Rsp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Rsp [code=" + code + ", msg=" + msg + "]";
	}

}
