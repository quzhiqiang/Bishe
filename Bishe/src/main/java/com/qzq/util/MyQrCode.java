package com.qzq.util;


import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/*
 * 生成二维码的类
 * Created by 屈志强 on 16/10/9.
 * */

public class MyQrCode {
		
	    /**
	     * 生成带logo的二维码
	     * */
	    public ServletOutputStream getimgqcode(HttpServletResponse resp, String content,String imgPath){
	        ServletOutputStream stream = null;
	        try {
				stream = resp.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        QzqQrCode sc = new QzqQrCode();
	        sc.encode(content, 300, 300, imgPath,stream, false);
	        return stream;
	    }

}
