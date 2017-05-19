package com.qzq.mapple.impl;


import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qzq.bean.FileComment;
import com.qzq.bean.FileInformation;
import com.qzq.bean.Page;
import com.qzq.mapple.FileMapple;
import com.qzq.util.GetPageSize;

@Component
public class FileMappleImpl implements FileMapple{
	
	@Autowired
	private GetPageSize getPageSize;
	
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name="sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public String userIsExist(String userName){
		String str = sqlSessionTemplate.selectOne("userBasic.userIsExist",userName);
		String flag = "";
		flag = (str==null)?"noexist":"exist";
		return flag;
	}

	public List<FileInformation> getFileList(Long pageNum) {
		Page page = new Page();
		Long pageSize = getPageSize.getFilePageSize();
		page.setPageSize(pageSize);
		page.setStart((pageNum-1)*pageSize);
	/*	String size = sqlSessionTemplate.selectOne("fileInfo.getFileListTotalSize");
		System.out.println(size);*/
		List<FileInformation> list = sqlSessionTemplate.selectList("fileInfo.getFileList",page);
		return list;
	}

	public Long getFileTotalSize() {
		return  Long.parseLong((String) sqlSessionTemplate.selectOne("fileInfo.getFileListTotalSize"));
	}
	
	/**
	 * 查询UUID对应的文件名
	 * */
	public String getFileName(String fileId) {
		return sqlSessionTemplate.selectOne("fileInfo.getFileName", fileId);
	}
	
	/**
	 * 保存上传文件信息
	 * */
	public int saveFileInfo(FileInformation fileInfo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("fileInfo.saveFileInfo", fileInfo);
	}
	
	public FileInformation getFileDetail(String fileId) {
		return  sqlSessionTemplate.selectOne("fileInfo.getFileDetail",fileId);
	}
	/**
	 * 保存用户对文件的评论
	 * */
	public int insertComment(FileComment fileComment) {
		return sqlSessionTemplate.insert("fileComment.insertComment",fileComment);
	}
	/**
	 * 查询对当前文件的评论
	 * **/
	public List<FileComment> getComment(String fileId) {
		return sqlSessionTemplate.selectList("fileComment.getComment",fileId);
	}

}
