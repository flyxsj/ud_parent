package com.ud.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.ud.manage.service.DriverService;
import com.ud.manage.service.StudentService;

public class BaseAction {

	public static final String ACCOUNT_SEESION_ID = "session_user_id";
	public static final String SESSION_VERIFY_CODE = "session_verify_code";

	private static final Logger logger = LoggerFactory.getLogger(BaseAction.class);

	public static final int LIST_PAGE_SIZE = 10;

	@Autowired
	protected DriverService driverService;

	@Autowired
	protected StudentService studentService;

	/**
	 * execute common action
	 * 
	 * @param model
	 * @param response
	 * @param request
	 */
	protected void commonAction(Model model, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("contextPath", request.getContextPath());
	}

	/**
	 * response json text
	 * 
	 * @param response
	 * @param text
	 */
	protected void responseJsonText(HttpServletResponse response, String text) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	protected void responseJsonWithCodeAndDataStr(HttpServletResponse response, String code, String jsonDataStr) {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject jo = new JSONObject();
			jo.put("code", code);
			jo.put("data", jsonDataStr);
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected void responseJsonWithCodeAndDataMap(HttpServletResponse response, String code, Map<String, String> map) {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject jo = new JSONObject();
			jo.put("code", code);
			/*
			 * Set<Entry<String, String>> entrySet = map.entrySet();
			 * Iterator<Entry<String, String>> iterator = entrySet.iterator();
			 * while (iterator.hasNext()) { Map.Entry<String, String> entry =
			 * (Map.Entry<String, String>) iterator.next();
			 * jo.put(entry.getKey(), entry.getValue()); }
			 */
			jo.put("data", JSONObject.fromObject(map).toString());
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected void responseJsonWithCodeAndMsg(HttpServletResponse response, String code, String msg) {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject jo = new JSONObject();
			jo.put("code", code);
			jo.put("msg", msg);
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected void responseJson4Success(HttpServletResponse response) {
		responseJsonWithCodeAndDataStr(response, "ok", "{}");
	}

	protected void responseJsonWithCode(HttpServletResponse response, String code) {
		responseJsonWithCodeAndDataStr(response, code, "{}");
	}

	protected void responseJson4Fail(HttpServletResponse response) {
		responseJsonWithCode(response, "fail");
	}

}
