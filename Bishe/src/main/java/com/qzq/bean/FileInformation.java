package com.qzq.bean;

import java.util.Date;

public class FileInformation extends Page{
	
	private String fileId;
	private String fileName;
	private Long fileSize;
	private String fileStyle;
	private String userId;
	private Date time;
	private Long downloads;
	private String fileDetail;
	
	public String getFileDetail() {
		return fileDetail;
	}

	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}

	private String userName;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileStyle() {
		return fileStyle;
	}

	public void setFileStyle(String fileStyle) {
		this.fileStyle = fileStyle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getDownloads() {
		return downloads;
	}

	public void setDownloads(Long downloads) {
		this.downloads = downloads;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
