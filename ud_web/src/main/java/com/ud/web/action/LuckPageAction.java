/**
 * 
 */

package com.ud.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ud.manage.service.LuckService;

/**
 * 
 * @author 21xsj@163.com
 * 
 */
@Controller
@RequestMapping("/luck")
public class LuckPageAction extends BaseAction {

	@Autowired
	protected LuckService luckService;

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		commonAction(model, response, request);
		return "luck/index";
	}
}
