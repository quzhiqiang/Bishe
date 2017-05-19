package com.qzq.bean;

import java.util.Date;

public class UserInformation extends UserBasicInformation{
	
	private Long id;
	private Long empiricalValue ;
	private Integer signIn;
	private Integer closeAccount;
	private Date closeAccountDate;
	private Date lastLoginTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmpiricalValue() {
		return empiricalValue;
	}
	public void setEmpiricalValue(Long empiricalValue) {
		this.empiricalValue = empiricalValue;
	}
	public Integer getSignIn() {
		return signIn;
	}
	public void setSignIn(Integer signIn) {
		this.signIn = signIn;
	}
	public Integer getCloseAccount() {
		return closeAccount;
	}
	public void setCloseAccount(Integer closeAccount) {
		this.closeAccount = closeAccount;
	}
	public Date getCloseAccountDate() {
		return closeAccountDate;
	}
	public void setCloseAccountDate(Date closeAccountDate) {
		this.closeAccountDate = closeAccountDate;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}
