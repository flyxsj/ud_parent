/**
 * 
 */

package com.ud.admin.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestAction extends BaseAction {

	@RequestMapping("/index.do")
	public String addPage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "name") String name) {
		return "test/index";
	}

}
