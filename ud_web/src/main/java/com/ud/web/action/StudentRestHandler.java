/**
 * 
 */

package com.ud.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.manage.domain.Student;
import com.ud.manage.service.StudentService;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/student/rest")
public class StudentRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(StudentRestHandler.class);
	@Autowired
	protected StudentService studentService;

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Student student = studentService.getById(id);
			return new RestResult("ok", student);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/register.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, Student student) {
		try {
			Student entity = studentService.getByOpenid(student.getOpenid());
			if (entity != null) {
				return new RestResult("existing");
			}
			studentService.register(student);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/study_award.do")
	public RestResult studyAward(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			// TODO get student info from session
			Student student = studentService.getById(2);
			String status = studentService.createStudyAward(student);
			if (status.equals("awarded")) {
				return new RestResult("awarded");
			}
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}
}
