/**
 * 
 */

package com.ud.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.manage.domain.Answer;
import com.ud.manage.domain.Exam;
import com.ud.manage.domain.Race;
import com.ud.manage.domain.Student;
import com.ud.manage.service.ExamService;
import com.ud.util.PageInfo;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/exam/rest")
public class ExamRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(ExamRestHandler.class);
	@Autowired
	protected ExamService examService;

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Exam exam = examService.getById(id);
			return new RestResult("ok", exam);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/submitAnswer.do")
	public RestResult submitAnswer(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id, @RequestParam(value = "answer") String answer,
			@RequestParam(value = "type", required = false, defaultValue = "simulate") String type) {
		try {
			// TODO per type to choose session uuid
			String examSessionUUID = request.getSession().getAttribute(ExamPageAction.SESSION_SIMULATE_EXAM_ID)
					.toString();
			// TODO the openid of student,should get it from weixin api
			if (examService.getRemainingTime(examSessionUUID) <= 0) {
				return new RestResult("timeout");
			}
			String openid = "aaa";
			Student student = studentService.getByOpenid(openid);
			Exam exam = examService.getById(id);
			if (examSessionUUID == null || student == null || exam == null) {
				throw new RuntimeException("Invalid submitAnswer request");
			}
			Answer a = new Answer(id, student.getId(), openid, answer, new Date());
			examService.saveAnswer(examSessionUUID, a);
			Map<String, String> map = new HashMap<String, String>();
			map.put("answer", exam.getAnswer());
			return new RestResult("ok", map);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/endExam.do")
	public RestResult endExam(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "type", required = false, defaultValue = "simulate") String type) {
		try {
			// TODO per type to choose session uuid
			String examSessionUUID = request.getSession().getAttribute(ExamPageAction.SESSION_SIMULATE_EXAM_ID)
					.toString();
			if (examService.getRemainingTime(examSessionUUID) <= 0) {
				return new RestResult("timeout");
			}
			Race race = examService.endExam("simulate", examSessionUUID);
			Map<String, String> map = new HashMap<String, String>();
			map.put("score", race.getScore() + "");
			map.put("time", race.getEndTime().getTime() - race.getStartTime().getTime() + "");
			return new RestResult("ok", map);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/getSimulateExamList.do")
	public RestResult getSimulateExamList(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "pageIndex") int pageIndex) {
		try {
			String examSessionId = request.getSession().getAttribute(ExamPageAction.SESSION_SIMULATE_EXAM_ID)
					.toString();
			PageInfo p = new PageInfo(3, 10, pageIndex);
			List<Exam> examList = examService.getSimulateExamList(examSessionId, p.getFromIndex(), p.getPageSize());
			return new RestResult("ok", examList);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/getRemaingTime.do")
	public RestResult getRemaingTime(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String examSessionId = request.getSession().getAttribute(ExamPageAction.SESSION_SIMULATE_EXAM_ID)
					.toString();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("time", examService.getRemainingTime(examSessionId));
			return new RestResult("ok", map);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}
}
