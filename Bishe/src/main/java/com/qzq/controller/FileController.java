package com.qzq.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.qzq.bean.FileComment;
import com.qzq.bean.FileInformation;
import com.qzq.bean.Page;
import com.qzq.service.FileService;
import com.qzq.util.GetFileAndEmail;
import com.qzq.util.GetPageSize;

/*
 * 文件上传下载查询控制器
 * */
@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private GetFileAndEmail getFileAndEmail;

	@Autowired
	private FileService fileServiceImpl;
	
	@Autowired
	private GetPageSize getPageSize;

	@RequestMapping("/download/{fileId}")
	public ResponseEntity<byte[]> dowloadFile(@PathVariable String fileId,HttpServletRequest request){
		String fileName = fileServiceImpl.getFileName(fileId);
		if(fileName!=null){
			File file = new File(getFileAndEmail.getUploadFilePath()+fileId);
			if(file.exists()){
				String dfileName = null;
				try {
					dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} 
				HttpHeaders headers = new HttpHeaders(); 
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
				headers.setContentDispositionFormData("attachment", dfileName); 
				try {
					return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					return new ResponseEntity<byte[]>("<div align='center' style='margin-top:20%;'><h2>抱歉,文件不存在<h2></div>".getBytes("gb2312"),HttpStatus.OK);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			try {
				return new ResponseEntity<byte[]>("<div align='center' style='margin-top:20%;'><h2>系统未知异常<h2></div>".getBytes("gb2312"),HttpStatus.OK);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getFileList(Long pageNum){
		if(pageNum==null){
			pageNum =(long) 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page();
		Long totalSize = fileServiceImpl.getFileTotalSize();
		Long pageSize = getPageSize.getFilePageSize();
		double c = (double)totalSize/(double)pageSize;
		page.setTotalSize(totalSize);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setPageTotal((long) Math.ceil(c));
		map.put("page",page);
		map.put("fileList", fileServiceImpl.getFileList(pageNum));
		return map;
	}
	
	@RequestMapping("/getDetail/{fileId}")
	@ResponseBody
	public FileInformation getFileDetail(@PathVariable String fileId){
		return fileServiceImpl.getFileDetail(fileId);
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String uploadFile1(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,String fileIntroduce,HttpSession session) {
		CommonsMultipartResolver mutilpartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 
		if (mutilpartResolver.isMultipart(request)){  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
			Iterator<String> it = multiRequest.getFileNames();
			int flag = 0;
			while (it.hasNext()) {  
				MultipartFile fileDetail = multiRequest.getFile(it.next()); 
				if (fileDetail != null) {  
					String fileName = UUID.randomUUID().toString();  
					String path = getFileAndEmail.getUploadFilePath();  
					File localFile = new File(path+fileName);  
					try {
						fileDetail.transferTo(localFile);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//将上传文件信息保存到数据库
					FileInformation fileInfo = new FileInformation();
					fileInfo.setFileId(fileName);
					fileInfo.setFileName(fileDetail.getOriginalFilename());
					fileInfo.setFileStyle(fileDetail.getContentType());
					fileInfo.setTime(new Date());
					fileInfo.setFileDetail(fileIntroduce);
					fileInfo.setFileSize(fileDetail.getSize());
					fileInfo.setUserId((String) session.getAttribute("userId"));
					int i = 0;
					try {
						i = fileServiceImpl.saveFileInfo(fileInfo);
					} catch (Exception e) {
						i = 0;
						e.printStackTrace();
					}
					if(i==1){
						flag = 1;
					}
					else{
						File file2 = new File(path+fileName);
						if(file2.exists()){
							file2.delete();
						}
						flag = 0;
					}
				}  
				else{
					flag = 0;
				}
			}  
			return flag==1?"success":"抱歉,上传文件失败,请重新尝试!!";
		}  
		else{
			return "上传错误";
		}
	}
	
	@RequestMapping("/saveComment")
	@ResponseBody
	public String saveComment(String fileId,String comment,HttpSession session){
		//获取用户ID
		String userId = (String) session.getAttribute("userId");
		int flag = 0;
		String returnMessage = null;
		if(userId==null){
			returnMessage = "101!";
			return returnMessage;
		}
		else{
			FileComment fileComment = new FileComment();
			fileComment.setFileId(fileId);
			fileComment.setUserId(userId);
			fileComment.setMessage(comment);
			fileComment.setMessageDate(new Date());
			flag = fileServiceImpl.insertComment(fileComment);
		if(flag == 1){
			returnMessage = "102";
		}
		else{
			returnMessage = "103";
		}
		return returnMessage;
		}
	}
	
	@RequestMapping("/getComment/{fileId}")
	@ResponseBody
	public List<FileComment> getComment(@PathVariable("fileId") String fileId){
		System.out.println(fileId);
		if(fileId == null){
			return null;
		}
		else{
			return fileServiceImpl.getComment(fileId);
		}
	}
}
