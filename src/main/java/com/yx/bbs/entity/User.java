package com.yx.bbs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "account", unique = true, nullable = false)
	private String userName;

	private String password;

	@Column(nullable = false)
	private String name;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "r_time", nullable = false)
	private Timestamp registTime;

	public User() {
		super();
	}

	public User(String userName, String password, String name, String status, Timestamp registTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.status = status;
		this.registTime = registTime;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name + ", status="
				+ status + ", registTime=" + registTime + "]";
	}

}
