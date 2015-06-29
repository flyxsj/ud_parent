package com.ud.manage.domain;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private String type;
	private String title;
	private String content;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
