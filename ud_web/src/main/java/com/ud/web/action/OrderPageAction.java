/**
 * 
 */

package com.ud.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ud.manage.domain.Driver;
import com.ud.manage.domain.Order;
import com.ud.manage.domain.Student;
import com.ud.manage.service.OrderService;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderPageAction extends BaseAction {

	@Autowired
	protected OrderService orderService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "type") String type,
			@RequestParam(value = "status", required = false, defaultValue = "reserved") String status,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = 0;
		List<Order> dataList = null;
		PageInfo page = null;
		if (type.equals("driver")) {
			Driver driver = driverService.getByOpenid(openid);
			page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
			count = orderService.getCountByDriver(driver.getId(), status);
			dataList = orderService.getListByDriver(driver.getId(), status, page.getFromIndex(), page.getPageSize());
			model.addAttribute("entity", driver);
		} else {
			Student student = studentService.getByOpenid(openid);
			page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
			count = orderService.getCountByStudent(student.getId(), status);
			dataList = orderService.getListByStudent(student.getId(), status, page.getFromIndex(), page.getPageSize());
			model.addAttribute("entity", student);
		}
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		model.addAttribute("type", type);
		model.addAttribute("status", status);
		model.addAttribute("openid", openid);
		return "order/list";
	}

	@RequestMapping("/book.do")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "order/book";
	}

	@RequestMapping("/comment.do")
	public String comment(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "orderNumber") String orderNumber) {
		commonAction(model, response, request);
		Order order = orderService.getByOrderNumber(orderNumber);
		model.addAttribute("entity", order);
		model.addAttribute("driver", driverService.getById(order.getDriverId()));
		return "order/comment";
	}
}
