package com.ud.admin.web.form;

public class RestResult {
	private String code = "ok";
	private Object data;
	private String message = "";

	public RestResult() {
	}

	public RestResult(String code) {
		this.code = code;
	}

	public RestResult(Object data) {
		this.data = data;
	}

	public RestResult(String code, Object data) {
		this.code = code;
		this.data = data;
	}

	public RestResult(String code, Object data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
