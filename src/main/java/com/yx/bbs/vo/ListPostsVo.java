package com.yx.bbs.vo;

import com.yx.bbs.entity.Posts;

public class ListPostsVo {

	// 主键
	private long id;
	// 标题
	private String title;
	// 作者
	private String author;
	// 创建时间
	private String createTime;
	// 回复数
	private int replyCount;
	// 收藏数
	private int appreciateCount;
	// 分类
	private String sort;
	// 模块
	private String module;

	// 模块id
	private long moduleId;
	// 分类id
	private long sortId;

	// 内容
	private String content;

	public ListPostsVo() {
	}

	public ListPostsVo(Posts p, String author, String sort, String module) {
		this.id = p.getId();
		this.title = p.getTitle();
		this.createTime = p.getCreateTime().toString().substring(0, 19);
		this.replyCount = p.getReplyCount();
		this.appreciateCount = p.getAppreciateCount();
		this.author = author;
		this.sort = sort;
		this.module = module;
		this.sortId = p.getSort();
		this.moduleId = p.getModule();
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getSortId() {
		return sortId;
	}

	public void setSortId(long sortId) {
		this.sortId = sortId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
