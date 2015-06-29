/**
 * 
 */

package com.ud.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.manage.domain.Luck;
import com.ud.manage.domain.Student;
import com.ud.manage.service.LuckService;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/luck/rest")
public class LuckRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(LuckRestHandler.class);
	@Autowired
	protected LuckService luckService;

	@RequestMapping("/getLuckBean.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "openid") String openid) {
		try {
			Student student = studentService.getByOpenid(openid);
			if (student == null) {
				return new RestResult("not_student");
			}
			List<Luck> list = luckService.getListByStudent(student.getId(), new Date());
			if (CollectionUtils.isNotEmpty(list)) {
				return new RestResult("has_got");
			}
			Luck luck = luckService.getLuck(student);
			return new RestResult("ok", luck);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/share.do")
	public RestResult share(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "luckId") int luckId) {
		try {
			Luck luck = luckService.getById(luckId);
			if (luck == null) {
				return new RestResult("not_existing");
			}
			luckService.shareSuccess(luck);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

}
