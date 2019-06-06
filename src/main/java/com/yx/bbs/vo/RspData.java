package com.yx.bbs.vo;

import com.yx.bbs.constant.RspType;

public class RspData<T> {

	// 0表示成功
	private int code;
	// 提示信息
	private String msg;

	private long count;

	private T data;

	public RspData(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public RspData(String msg) {
		this.code = -1;
		this.msg = msg;
	}

	public RspData(RspType type) {
		this.code = type.getCode();
		this.msg = type.name();
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RspData [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
