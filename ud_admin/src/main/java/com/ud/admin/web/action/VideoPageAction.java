/**
 * 
 */

package com.ud.admin.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ud.manage.domain.Video;
import com.ud.manage.service.VideoService;
import com.ud.util.PageInfo;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/admin/video")
public class VideoPageAction extends BaseAction {

	@Autowired
	protected VideoService videoService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
		commonAction(model, response, request);
		int count = videoService.getCount();
		PageInfo page = new PageInfo(LIST_PAGE_SIZE, count, pageIndex);
		List<Video> dataList = videoService.getList(page.getFromIndex(), page.getPageSize());
		model.addAttribute("dataList", dataList);
		model.addAttribute("pageInfo", page);
		return "video/list";
	}

	@RequestMapping("/add.do")
	public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "video/add";
	}

	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		commonAction(model, response, request);
		Video video = videoService.getById(id);
		model.addAttribute("entity", video);
		return "video/edit";
	}
}
