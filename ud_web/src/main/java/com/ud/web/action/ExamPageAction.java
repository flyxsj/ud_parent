/**
 * 
 */

package com.ud.web.action;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ud.manage.service.ExamService;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/exam")
public class ExamPageAction extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(ExamPageAction.class);
	public static final String SESSION_SIMULATE_EXAM_ID = "simulate_exam_id";

	@Autowired
	protected ExamService examService;

	@RequestMapping("/simulate/index.do")
	public String simulateIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		HttpSession session = request.getSession();
		Object existingUUID = session.getAttribute(SESSION_SIMULATE_EXAM_ID);
		if (existingUUID != null) {
			logger.warn("{} exam session has been cleared", existingUUID);
			examService.clearExamSessionInfo(existingUUID.toString());
		}
		String examUUID = UUID.randomUUID().toString();
		session.setAttribute(SESSION_SIMULATE_EXAM_ID, examUUID);
		examService.initSimulateExam("simulate", examUUID);
		return "exam/index";
	}

	@RequestMapping("/event/index.do")
	public String eventIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);		
		return "exam/event/index";
	}
}
