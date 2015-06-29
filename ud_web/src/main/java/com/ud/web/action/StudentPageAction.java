/**
 * 
 */

package com.ud.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ud.manage.domain.Exam;
import com.ud.manage.domain.Student;
import com.ud.manage.service.ExamService;
import com.ud.manage.service.StudentService;
import com.ud.manage.service.WeiXinService;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/student")
public class StudentPageAction extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(StudentPageAction.class);

	@Autowired
	protected StudentService studentService;

	@Autowired
	protected ExamService examService;

	@Autowired
	protected WeiXinService weiXinService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = studentService.getCount();
		PageInfo page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
		List<Student> dataList = studentService.getList(page.getFromIndex(), page.getPageSize());
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		return "student/list";
	}

	@RequestMapping("/study.do")
	public String study(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "type", required = false, defaultValue = "course1") String type,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = examService.getCountByType(type);
		PageInfo page = new PageInfo(2, count, pageIndex);
		List<Exam> dataList = examService.getListByType(type, page.getFromIndex(), page.getPageSize());
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		return "student/study";
	}

	/**
	 * code参数是微信网页授权返回的授权码，通过这个code去获取openid
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param authCode
	 * @return
	 */
	@RequestMapping("/register.do")
	public String register(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "code", required = false, defaultValue = "0") String authCode) {
		commonAction(model, response, request);
		String openid = weiXinService.getOpenidByAuthCode(authCode);
		logger.info("======get openid:" + openid);
		// TODO need to be removed
		openid = "testopenid";
		model.addAttribute("openid", openid);
		return "student/register";
	}
}
