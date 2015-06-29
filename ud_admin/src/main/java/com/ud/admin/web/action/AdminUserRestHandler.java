/**
 * 
 */

package com.ud.admin.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.admin.web.form.RestResult;
import com.ud.manage.domain.AdminUser;
import com.ud.util.MD5;

@RestController
@RequestMapping("/admin/user")
public class AdminUserRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserRestHandler.class);

	/**
	 * admin account login action
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.do")
	public RestResult login(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
		String md5 = MD5.md5(password);
		AdminUser user = adminUserService.getByUsername(username);
		if (user == null || md5.equals(user.getPassword()) == false) {
			return new RestResult("unvalid_user");
		}
		request.getSession().setAttribute(BaseAction.ACCOUNT_SEESION_ID, user);
		return new RestResult("ok");
	}

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			AdminUser user = adminUserService.getById(id);
			return new RestResult("ok", user);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/delete.do")
	public RestResult delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			adminUserService.delete(id);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, AdminUser adminUser) {
		try {
			adminUserService.create(adminUser);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/update.do")
	public RestResult update(HttpServletRequest request, HttpServletResponse response, Model model, AdminUser adminUser) {
		try {
			adminUserService.update(adminUser);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

}
