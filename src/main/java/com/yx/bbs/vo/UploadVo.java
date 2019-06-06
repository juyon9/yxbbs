package com.yx.bbs.vo;

public class UploadVo {

	private String src;
	private String title;

	public UploadVo(String src) {
		super();
		this.src = src;
		this.title = "";
	}

	public UploadVo(String src, String title) {
		super();
		this.src = src;
		this.title = title;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
