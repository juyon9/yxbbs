package com.yx.bbs.vo;

import com.yx.bbs.entity.User;

public class SessionUser {

	private long id;
	private String userName;
	private String name;
	private String status;

	public SessionUser() {
	}

	public SessionUser(User user) {
		super();
		this.id = user.getId();
		this.userName = user.getUserName();
		this.name = user.getName();
		this.status = user.getStatus();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
