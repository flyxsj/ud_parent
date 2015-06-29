/**
 * 
 */

package com.ud.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.manage.service.CommentService;

import com.ud.web.form.RestResult;
import com.ud.manage.domain.Comment;
import com.ud.util.MD5;

@RestController
@RequestMapping("/comment/rest")
public class CommentRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(CommentRestHandler.class);
	@Autowired
	protected CommentService commentService;

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Comment comment = commentService.getById(id);
			return new RestResult("ok", comment);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/create.do")
	public RestResult create(HttpServletRequest request, HttpServletResponse response, Model model, Comment comment) {
		try {
			commentService.create(comment);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}
}
