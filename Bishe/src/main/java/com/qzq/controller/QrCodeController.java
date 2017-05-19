package com.qzq.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzq.util.MyQrCode;

/*
 * 二维码控制器
 * */
@Controller
@RequestMapping("/qrCode")
public class QrCodeController {
	
	@RequestMapping("/get")
	public void getqcode(HttpServletResponse resp, String fileId,HttpServletRequest req) throws IOException {
		MyQrCode code = new MyQrCode();
		String codeIconPath =  req.getServletContext().getRealPath("/") + "/image/headPortrait/default.jpg";
		String url = "http://192.168.253.1:8080/Bishe/file/download/"+fileId;
		ServletOutputStream sos = code.getimgqcode(resp, url, codeIconPath);
		sos.flush();
		sos.close();
	}

}
