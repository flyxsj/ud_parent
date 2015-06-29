package com.ud.admin.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ud.admin.web.action.BaseAction;
import com.ud.admin.web.form.RestResult;
import com.ud.manage.domain.AdminUser;

public class AccessFilter implements Filter {

	private static List<String> ignoredURI = new ArrayList<String>();

	private static final String LOGIN_URI = "/admin/user/logindex.do";

	private static List<String> ignoredPath = new ArrayList<String>();

	static {
		ignoredPath.add("/admin/user/login.do");
		ignoredPath.add(LOGIN_URI);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestType = request.getHeader("X-Request-Type");
		for (String path : ignoredPath) {
			ignoredURI.add(request.getContextPath() + path);
		}
		AdminUser user = (AdminUser) request.getSession().getAttribute(BaseAction.ACCOUNT_SEESION_ID);
		// AccountUtil.setAdminAccount(user);
		boolean isLogin = false;
		String uri = request.getRequestURI();
		boolean isIgnore = ignoredURI.contains(uri);
		if (!isIgnore) {
			if (user != null) {
				isLogin = true;
			}
			if (!isLogin) {
				String loginUrl = request.getContextPath() + LOGIN_URI;
				PrintWriter writer = response.getWriter();
				if ("ajax".equals(requestType)) {
					response.setCharacterEncoding("utf8");
					response.setContentType("application/json");
					ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(writer, new RestResult("timeout"));
				} else {
					response.setContentType("text/html; charset=UTF-8");
					writer.write("<script language='javascript'>");
					writer.write("alert('请先登录');");
					writer.write("window.parent.location.href='" + loginUrl + "';");
					writer.write("</script>");
				}
				writer.close();
				return;
			}
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
