package com.biyeseng.orm;

/**
 * 新闻公告Bean
 */
public class Tgonggao {
	// 新闻公告ID
	private String id;
	// 新闻公告标题
	private String title;
	// 新闻公告内容
	private String content;
	// 新闻公告时间
	private String shijian;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
