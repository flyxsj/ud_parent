/**
 * 
 */

package com.ud.admin.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.admin.web.form.RestResult;
import com.ud.manage.domain.AdminUser;
import com.ud.manage.service.AdminUserService;

@RestController
@RequestMapping("/test/rest")
public class TestRestHandler extends BaseAction {

	@Autowired
	protected AdminUserService adminUserService;

	@RequestMapping("/greeting.action")
	public RestResult greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		List<AdminUser> adminAccountList = adminUserService.getList(0, 5);
		System.out.println("======" + adminAccountList);
		return new RestResult(adminAccountList);
	}

}
