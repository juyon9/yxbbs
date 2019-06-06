package com.yx.bbs.constant;

public enum PageInfo {

	每页条数(10);

	// 每页显示条数
	private int countInPage;

	private PageInfo(int countInPage) {
		this.countInPage = countInPage;
	}

	public int getCountInPage() {
		return countInPage;
	}

	public void setCountInPage(int countInPage) {
		this.countInPage = countInPage;
	}

}
