package com.ud.test.service;

import org.junit.Test;

import com.ud.test.BaseTest;
import com.ud.util.ToolsUtil;

public class RaceServiceTest extends BaseTest {

	@Test
	public void handleSimulateRank() {
		raceService.handleSimulateRank(ToolsUtil.toDate("2015-07-27", "yyyy-MM-dd"));
	}

	@Test
	public void handleEventRank() {
		raceService.handleEventRank(ToolsUtil.toDate("2015-07-27", "yyyy-MM-dd"));
	}

}
