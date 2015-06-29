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

import com.ud.manage.service.ScheduleService;

import com.ud.admin.web.form.RestResult;
import com.ud.manage.domain.Schedule;
import com.ud.util.MD5;

@RestController
@RequestMapping("/admin/schedule")
public class ScheduleRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleRestHandler.class);
	@Autowired
	protected ScheduleService scheduleService;

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Schedule schedule = scheduleService.getById(id);
			return new RestResult("ok", schedule);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/delete.do")
	public RestResult delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			scheduleService.delete(id);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, Schedule schedule) {
		try {
			scheduleService.create(schedule);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/update.do")
	public RestResult update(HttpServletRequest request, HttpServletResponse response, Model model, Schedule schedule) {
		try {
			scheduleService.update(schedule);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

}
