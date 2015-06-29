package com.ud.manage.domain;

import java.io.Serializable;
import java.util.*;

public class AdminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882396732587423980L;

	private Integer id;
	private String username;
	private String password;
	private String roleType;
	private String remark;

	public AdminUser() {
	}

	public AdminUser(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	public void setRoleType(String value) {
		this.roleType = value;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}
}
