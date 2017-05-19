package com.qzq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.qzq.service.UserService;

@Controller
public class TimingTaskController {
	
	@Autowired
	private UserService userServiceImpl;

	/*
	 * 每日凌晨零时刷新签到表
	 * */
	@Scheduled(cron = "0 0 0 * * ?")  
	public void updateSignIn() {  
		//刷新签到表 
		userServiceImpl.updateSignIn();
	}
	
}
