package com.qzq.mapple;


import java.util.List;

import com.qzq.bean.Invitation;
public interface InvitationMapple {
	
	public List<Invitation> getInvitationList();
	
	public int saveInvitation(Invitation invitation);
	
}
