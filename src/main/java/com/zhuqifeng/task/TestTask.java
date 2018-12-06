package com.zhuqifeng.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	protected Logger logger = LogManager.getLogger(getClass());

	@Scheduled(cron = "0 5/20 * * * ?")
	public void cronTest() {
	}
}
