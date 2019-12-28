package com.example.demo.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类: Md5Util <br>
 * 描述: MD5加密 <br>
 * 作者: Teacher song<br>
 * 时间: 2017年6月27日 下午2:27:06
 */
public class Md5Util {
	
	/**
	 * 
	 * 方法: getMd532 <br>
	 * 描述: 32位md5加密 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年6月27日 下午2:27:38
	 * @param plainText
	 * @return
	 */
	public static String getMd532(String plainText) {  
	    try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(plainText.getBytes());  
	        byte b[] = md.digest();  
	        int i;
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < b.length; offset++) {  
	            i = b[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	        }  
	        //32位加密  
	        return buf.toString();  
	        // 16位的加密  
	        //return buf.toString().substring(8, 24);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	}  
	/**
	 * 
	 * 方法: getMd516 <br>
	 * 描述: 16位MD5加密 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年6月27日 下午2:27:52
	 * @param plainText
	 * @return
	 */
	public static String getMd516(String plainText) {  
	    try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(plainText.getBytes());  
	        byte b[] = md.digest();  
	        int i;  
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < b.length; offset++) {  
	            i = b[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	        }  
	        //32位加密  
	       // return buf.toString();  
	        // 16位的加密  
	        return buf.toString().substring(8, 24);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	}
}
