package com.qzq.bean;

import java.util.Date;

public class UserBasicInformation {
	private String userId;
	private String userName;
	private String passwordEncryption;
	private String email;
	private String sex;
	private Date registerDate;
	private Integer jurisdiction;
	private String picture;
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswordEncryption() {
		return passwordEncryption;
	}
	public void setPasswordEncryption(String passwordEncryption) {
		this.passwordEncryption = passwordEncryption;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(Integer jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
}
