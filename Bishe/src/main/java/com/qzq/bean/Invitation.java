package com.qzq.bean;

import java.util.Date;

public class Invitation {
	
	private Long invitationId;
	private String userId;
	private String headline;
	private String content;
	private Long browsingNumber;
	private Long likeNumber;
	private Date invitationDate;
	private Integer closeInvitation;
	
	private String userName;

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getBrowsingNumber() {
		return browsingNumber;
	}

	public void setBrowsingNumber(Long browsingNumber) {
		this.browsingNumber = browsingNumber;
	}

	public Long getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(Long likeNumber) {
		this.likeNumber = likeNumber;
	}

	public Date getInvitationDate() {
		return invitationDate;
	}

	public void setInvitationDate(Date invitationDate) {
		this.invitationDate = invitationDate;
	}

	public Integer getCloseInvitation() {
		return closeInvitation;
	}

	public void setCloseInvitation(Integer closeInvitation) {
		this.closeInvitation = closeInvitation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
