package com.ud.test.service;

import org.junit.Test;

import com.ud.manage.domain.Driver;
import com.ud.test.BaseTest;

public class DriverServiceTest extends BaseTest {

	@Test
	public void authPass() {
		Driver driver = driverService.getById(6);
		driverService.authPass(driver);
	}
	
}
