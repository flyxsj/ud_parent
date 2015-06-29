/**
 * 
 */

package com.ud.admin.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ud.manage.domain.AdminUser;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserPageAction extends BaseAction {

	/**
	 * login page action
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/logindex.do")
	public String logInex(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "login/index";
	}

	/**
	 * welcome page action
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/main.do")
	public String welcome(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "welcome/index";
	}

	/**
	 * logout action
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		request.getSession().invalidate();
		return "login/index";
	}

	@RequestMapping("/list.do")
	public String accountList(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = adminUserService.getCount();
		PageInfo page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
		List<AdminUser> accountList = adminUserService.getList(page.getFromIndex(), page.getPageSize());
		model.addAttribute("accountList", accountList);
		model.addAttribute("pageInfo", page);
		return "user/list";
	}

	@RequestMapping("/add.do")
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "user/add";
	}

	@RequestMapping("/edit.do")
	public String editUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		commonAction(model, response, request);
		AdminUser user = adminUserService.getById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping("/account/setting.do")
	public String setting(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "user/setting";
	}
}
