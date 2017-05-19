package com.qzq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qzq.bean.Invitation;
import com.qzq.service.InvitationService;

/*
 * 发帖控制器
 * */
@Controller
@RequestMapping("/inv")
public class InvitationController {

	@Autowired
	private InvitationService invitationServiceImpl;

	@RequestMapping("/getList")
	@ResponseBody
	public List<Invitation> getInvivationList(){
		return invitationServiceImpl.getInvitationList();
	}

}
