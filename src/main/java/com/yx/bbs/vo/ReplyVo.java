package com.yx.bbs.vo;

import com.yx.bbs.entity.Reply;

public class ReplyVo {
	// 回复人
	private String userName;
	// 回复内容
	private String content;
	// 回复时间
	private String replyTime;

	public ReplyVo(Reply reply, String name) {
		super();
		this.userName = name;
		this.content = reply.getContent();
		this.replyTime = reply.getReplyTime().toString().substring(0, 19);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

}
