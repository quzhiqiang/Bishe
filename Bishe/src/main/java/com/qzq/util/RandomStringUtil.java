package com.qzq.util;

import java.util.Random;

/**
 * 生成6位随机字符串的工具类
 * @author 屈志强
 *
 */
public class RandomStringUtil {

	public String getRandomString(){
		String s = "abcdefghigklmnopqrstuvwxyz0123456789";  
		StringBuffer sb = new StringBuffer();  
		Random m = new Random();  
		for(int i=0;i<5;i++){  
			int n = m.nextInt(s.length()-1);  
			sb.append(s.charAt(n));  
		}  
		return sb.toString();
	}

}
