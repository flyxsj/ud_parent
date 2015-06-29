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

import com.ud.manage.domain.Schedule;
import com.ud.manage.service.ScheduleService;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/schedule")
public class SchedulePageAction extends BaseAction {

	@Autowired
	protected ScheduleService scheduleService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = scheduleService.getCount();
		PageInfo page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
		List<Schedule> dataList = scheduleService.getList(page.getFromIndex(), page.getPageSize());
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		return "schedule/list";
	}

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "schedule/index";
	}
}
