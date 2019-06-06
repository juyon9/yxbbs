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
@Table(name = "t_reply")
public class Reply implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "p_id", nullable = false)
	private long postsId;

	@Column(name = "u_id", nullable = false)
	private long replyUser;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "c_time", nullable = false)
	private Timestamp replyTime;

	public Reply(long postsId, long replyUser, String content, Timestamp replyTime) {
		super();
		this.postsId = postsId;
		this.replyUser = replyUser;
		this.content = content;
		this.replyTime = replyTime;
	}

	public Reply() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostsId() {
		return postsId;
	}

	public void setPostsId(long postsId) {
		this.postsId = postsId;
	}

	public long getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(long replyUser) {
		this.replyUser = replyUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

}
