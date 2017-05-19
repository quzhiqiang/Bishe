package com.qzq.util;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
/** 
 * 功能简介：MD5加密工具类 
 * 密码等安全信息存入数据库时，转换成MD5加密形式 
 * @author  
 *  
 * 
 */  
public class Md5Util  {  
    public static String getMd5(String inStr)  
    {  
        String outStr=null;  
        if(inStr ==null)  
        {  
            outStr = null;  
        }  
        else if("".equals(inStr))  
        {  
            outStr = "";  
        }  
        else  
        {  
            try  
            {  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                md.update(inStr.getBytes());  
                byte b[] = md.digest();  
                StringBuffer buf = new StringBuffer();  
                for(int i=1;i<b.length;i++)  
                {  
                    int c = b[i]>>>4 & 0xf;  
                    buf.append(Integer.toHexString(c));  
                    c = b[i]& 0xf;  
                    buf.append(Integer.toHexString(c));  
                }  
                outStr = buf.toString();  
            }  
            catch (NoSuchAlgorithmException e)  
            {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return outStr;  
    }  
  
}
