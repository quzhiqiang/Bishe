package com.qzq.service;

import java.util.List;

import com.qzq.bean.FileComment;
import com.qzq.bean.FileInformation;

public interface FileService {
	
	public List<FileInformation> getFileList(Long pageNum);
	
	public Long getFileTotalSize();
	
	public FileInformation getFileDetail(String fileId);
	
	public String getFileName(String fileId);
	
	public int saveFileInfo(FileInformation fileInfo);
	
	public int insertComment(FileComment fileComment);
	
	public List<FileComment> getComment(String fileId);

}
