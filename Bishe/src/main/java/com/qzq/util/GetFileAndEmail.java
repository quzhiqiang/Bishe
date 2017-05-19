package com.qzq.util;
/**
 * 获取关于文件上传路径和设置发送邮件相关配置的工具类
 * */
public class GetFileAndEmail {
	
	private String uploadFilePath;
	
	private String emailHost;
	
	private String emailUser;
	
	private String emailPwd;

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailPwd() {
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}
	

}
