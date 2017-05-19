package com.qzq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 得到时间字符串的工具类
 * */

public class GetDate {
	
	public String getFormatdate(String format){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

}
