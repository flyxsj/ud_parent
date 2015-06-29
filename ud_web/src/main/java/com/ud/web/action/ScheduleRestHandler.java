/**
 * 
 */

package com.ud.web.action;

import java.util.Date;

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
import com.ud.manage.domain.Schedule;
import com.ud.manage.service.ScheduleService;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/schedule/rest")
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

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "driverId") int driverId, @RequestParam(value = "type") String type,
			@RequestParam(value = "day") String day, @RequestParam(value = "startTime") String startTime,
			@RequestParam(value = "endTime") String endTime,
			@RequestParam(value = "isAlwaysEnable", required = false, defaultValue = "false") boolean isAlwaysEnable) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm");
			Schedule s = new Schedule();
			s.setDriverId(driverId);
			s.setType(type);
			s.setDay(sdf.parse(day));
			s.setStartTime(time.parse(startTime));
			s.setEndTime(time.parse(endTime));
			s.setCreateTime(new Date());
			s.setIsAlwaysEnable(isAlwaysEnable);
			scheduleService.create(s);
			return new RestResult("ok");
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
}
