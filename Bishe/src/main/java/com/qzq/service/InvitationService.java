package com.qzq.service;

import java.util.List;

import com.qzq.bean.Invitation;

public interface InvitationService {
	
	public List<Invitation> getInvitationList();
	
	public int saveInvitation(Invitation invitation);
	

}
