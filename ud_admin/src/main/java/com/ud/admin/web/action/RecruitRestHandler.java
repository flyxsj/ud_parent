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

import com.ud.manage.service.RecruitService;

import com.ud.admin.web.form.RestResult;
import com.ud.manage.domain.Recruit;
import com.ud.util.MD5;

@RestController
@RequestMapping("/admin/recruit")
public class RecruitRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(RecruitRestHandler.class);
	@Autowired
	protected RecruitService recruitService;


	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Recruit recruit = recruitService.getById(id);
			return new RestResult("ok", recruit);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/delete.do")
	public RestResult delete(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			recruitService.delete(id);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, Recruit recruit) {
		try {
			recruitService.create(recruit);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/update.do")
	public RestResult update(HttpServletRequest request, HttpServletResponse response, Model model, Recruit recruit) {
		try {
			recruitService.update(recruit);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

}
