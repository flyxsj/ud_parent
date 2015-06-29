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

import com.ud.manage.domain.Recruit;
import com.ud.manage.service.RecruitService;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/recruit/rest")
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

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, Recruit recruit) {
		try {
			Date now = new Date();
			recruit.setStatus("I");
			recruit.setCreateTime(now);
			recruit.setUpdateTime(now);
			recruitService.create(recruit);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}
}
