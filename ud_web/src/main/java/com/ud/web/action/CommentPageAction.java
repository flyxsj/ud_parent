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

import com.ud.manage.service.CommentService;

import com.ud.manage.domain.Comment;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/comment")
public class CommentPageAction extends BaseAction {

	@Autowired
	protected CommentService commentService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = commentService.getCount();
		PageInfo page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
		List<Comment> dataList = commentService.getList(page.getFromIndex(), page.getPageSize());
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		return "comment/list";
	}

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "comment/index";
	}
}
