/**
 * 
 */

package com.ud.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.icu.text.SimpleDateFormat;
import com.ud.manage.domain.DriverMatchInfo;
import com.ud.manage.domain.Order;
import com.ud.manage.service.OrderService;
import com.ud.util.ToolsUtil;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/order/rest")
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

	@RequestMapping("/match.do")
	public RestResult match(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "type") String type, @RequestParam(value = "lon") double lon,
			@RequestParam(value = "lat") double lat, @RequestParam(value = "day") String day,
			@RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime) {
		try {
			Date s = ToolsUtil.toDate(startTime, "HH:mm");
			Date e = ToolsUtil.toDate(endTime, "HH:mm");
			Date date = ToolsUtil.toDate(day, "yyyy-MM-dd");
			List<DriverMatchInfo> list = orderService.matchDriver(type, lon, lat, date, s, e);
			return new RestResult("ok", list);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/reserve.do")
	public RestResult reserve(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "studentId") int studentId, @RequestParam(value = "driverId") int driverId,
			@RequestParam(value = "type") String type, @RequestParam(value = "course") String course,
			@RequestParam(value = "address") String address, @RequestParam(value = "lon") double lon,
			@RequestParam(value = "lat") double lat, @RequestParam(value = "day") String day,
			@RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm");
			Order o = new Order();
			o.setOrderNumber(orderService.getOrderNumber());
			o.setServiceCode(orderService.getServiceCode());
			o.setStudentId(studentId);
			o.setDriverId(driverId);
			o.setType(type);
			o.setCourse(course);
			o.setAddress(address);
			o.setLatitude(lat);
			o.setLongitude(lon);
			o.setReserveDay(sdf.parse(day));
			o.setReserveStartTime(time.parse(startTime));
			o.setReserveEndTime(time.parse(endTime));
			o.setStatus("reserved");
			o.setCreateTime(new Date());
			orderService.create(o);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/accept.do")
	public RestResult accept(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "orderNumber") String orderNumber) {
		try {
			Order order = orderService.getByOrderNumber(orderNumber);
			if (!order.getStatus().equals("reserved")) {
				throw new RuntimeException("Invalid order accept request.orderNumber=" + orderNumber);
			}
			order.setStatus("accepted");
			orderService.update(order);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/start.do")
	public RestResult start(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "orderNumber") String orderNumber,
			@RequestParam(value = "serviceCode") String serviceCode) {
		try {
			// TODO need to pass driver's open id to end order
			Order order = orderService.getByOrderNumber(orderNumber);
			if (!order.getStatus().equals("paid")) {
				throw new RuntimeException("Invalid order start request.orderNumber=" + orderNumber);
			}
			if (!order.getServiceCode().equals(serviceCode)) {
				return new RestResult("invalid_service_code");
			}
			order.setStatus("start");
			order.setTrainStartTime(new Date());
			orderService.update(order);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/end.do")
	public RestResult end(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "orderNumber") String orderNumber) {
		try {
			// TODO need to pass driver's open id to end order
			Order order = orderService.getByOrderNumber(orderNumber);
			if (!order.getStatus().equals("start")) {
				throw new RuntimeException("Invalid order end request.orderNumber=" + orderNumber);
			}
			order.setStatus("end");
			order.setTrainEndTime(new Date());
			orderService.update(order);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/comment.do")
	public RestResult comment(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "orderNumber") String orderNumber, @RequestParam(value = "level") String level,
			@RequestParam(value = "comment") String comment) {
		try {
			// TODO need to pass driver's open id to end order
			orderService.commentOrder(orderNumber, level, comment);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}
}
