package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private Integer orderId;
	private String level;
	private String description;
	private Date createTime;

	public Comment() {
	}

	public Comment(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setOrderId(Integer value) {
		this.orderId = value;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 获取分数奖励值
	 * 
	 * @return
	 */
	public int getAwardScore() {
		int score = 0;
		if (this.getLevel().equals("good")) {
			score = 10;
		} else if (this.getLevel().equals("bad")) {
			score = -20;
		}
		return score;
	}

	/**
	 * 获取U豆奖励值
	 * 
	 * @return
	 */
	public int getAwardBean() {
		return 10;
	}
}
