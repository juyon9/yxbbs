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
@Table(name = "t_posts")
public class Posts implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "author", nullable = false)
	private long author;

	@Column(name = "c_time", nullable = false)
	private Timestamp createTime;

	@Column(name = "r_cnt", nullable = false)
	private int replyCount;

	@Column(name = "a_cnt", nullable = false)
	private int appreciateCount;

	@Column(name = "s_id", nullable = false)
	private long sort;

	@Column(name = "m_id", nullable = false)
	private long module;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getAppreciateCount() {
		return appreciateCount;
	}

	public void setAppreciateCount(int appreciateCount) {
		this.appreciateCount = appreciateCount;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
	}

}
