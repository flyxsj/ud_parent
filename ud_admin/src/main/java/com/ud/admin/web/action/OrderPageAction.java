/**
 * 
 */

package com.ud.admin.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ud.manage.service.OrderService;

import com.ud.manage.domain.Order;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/admin/order")
public class OrderPageAction extends BaseAction {

	@Autowired
	protected OrderService orderService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = orderService.getCount();
		PageInfo page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
		List<Order> dataList = orderService.getList(page.getFromIndex(), page.getPageSize());
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		return "order/list";
	}

	@RequestMapping("/add.do")
	public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "order/add";
	}

	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		commonAction(model, response, request);
		Order order = orderService.getById(id);
		model.addAttribute("entity", order);
		return "order/edit";
	}
}
