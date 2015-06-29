/**
 * 
 */

package com.ud.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ud.manage.domain.Driver;
import com.ud.manage.service.DriverService;
import com.ud.web.form.RestResult;

@RestController
@RequestMapping("/driver/rest")
public class DriverRestHandler extends BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(DriverRestHandler.class);
	@Autowired
	protected DriverService driverService;

	@RequestMapping("/get.do")
	public RestResult get(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") int id) {
		try {
			Driver driver = driverService.getById(id);
			return new RestResult("ok", driver);
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/create.do")
	public RestResult register(HttpServletRequest request, HttpServletResponse response, Model model, Driver driver) {
		try {
			driver.setCreateTime(new Date());
			driverService.register(driver);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

	@RequestMapping("/update.do")
	public RestResult update(HttpServletRequest request, HttpServletResponse response, Model model, Driver driver) {
		try {
			driverService.update(driver);
			return new RestResult("ok");
		} catch (Exception e) {
			logger.error("", e);
			return new RestResult("internal_error");
		}
	}

}
