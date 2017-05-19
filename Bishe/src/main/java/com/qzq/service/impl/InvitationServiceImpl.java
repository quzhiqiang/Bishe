package com.qzq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qzq.bean.Invitation;
import com.qzq.mapple.InvitationMapple;
import com.qzq.service.InvitationService;

@Component
public class InvitationServiceImpl implements InvitationService{
	
	@Autowired
	private InvitationMapple invitationMappleImpl;

	public List<Invitation> getInvitationList() {
		return invitationMappleImpl.getInvitationList();
	}

	public int saveInvitation(Invitation invitation) {
		return invitationMappleImpl.saveInvitation(invitation);
	}
	
}
