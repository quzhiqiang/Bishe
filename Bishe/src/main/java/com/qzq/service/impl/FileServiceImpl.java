package com.qzq.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qzq.bean.FileComment;
import com.qzq.bean.FileInformation;
import com.qzq.mapple.FileMapple;
import com.qzq.service.FileService;

@Component
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileMapple fileMappleImpl;

	public List<FileInformation> getFileList(Long pageNum) {
		return fileMappleImpl.getFileList(pageNum);
	}
	
	public Long getFileTotalSize() {
		return fileMappleImpl.getFileTotalSize();
	}

	public String getFileName(String fileId) {
		return fileMappleImpl.getFileName(fileId);
	}

	public int saveFileInfo(FileInformation fileInfo) {
		return fileMappleImpl.saveFileInfo(fileInfo);
	}

	public FileInformation getFileDetail(String fileId) {
		return fileMappleImpl.getFileDetail(fileId);
	}

	public int insertComment(FileComment fileComment) {
		return fileMappleImpl.insertComment(fileComment);
	}

	public List<FileComment> getComment(String fileId) {
		return fileMappleImpl.getComment(fileId);
	}

}
