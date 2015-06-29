/**
 * 
 */

package com.ud.admin.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.manage.service.OrderService;

import com.ud.admin.web.form.RestResult;
import com.ud.manage.domain.Order;
import com.ud.util.MD5;

@RestController
@RequestMapping("/admin/order")
public class OrderRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(OrderRestHandler.class);
	@Autowired
	protected OrderService orderService;

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Order order = orderService.getById(id);
			return new RestResult("ok", order);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/delete.do")
	public RestResult delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			orderService.delete(id);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, Order order) {
		try {
			orderService.create(order);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/update.do")
	public RestResult update(HttpServletRequest request, HttpServletResponse response, Model model, Order order) {
		try {
			orderService.update(order);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

}
