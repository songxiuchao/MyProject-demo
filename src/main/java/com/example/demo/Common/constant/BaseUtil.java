package com.example.demo.Common.constant;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BaseUtil
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-09 11:49
 */
public class BaseUtil {

	/**
	 * 检查对象是否为空
	 * 
	 * @param pObj
	 * @return boolean
	 */
	public static boolean isEmpty(Object pObj) {
		if (null == pObj) {
			return true;
		}
		return false;
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param psValue
	 * @return boolean
	 */
	public static boolean isEmpty(String psValue) {
		if (null == psValue || psValue.trim().length() == 0 || ("null").equals(psValue)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查List是否为空
	 * 
	 * @param plstObj
	 * @return boolean
	 */
	public static boolean isEmpty(List<?> plstObj) {
		if (null == plstObj || plstObj.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检查Map是否为空
	 * 
	 * @param pmapObj
	 * @return boolean
	 */
	public static boolean isEmpty(Map<?, ?> pmapObj) {
		if (null == pmapObj || pmapObj.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 去除空格,换行
	 * 
	 * @param psValue
	 * @return String
	 */
	public static String transNoBlank(String psValue) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(psValue);
		String sNoBlank = m.replaceAll("");
		return sNoBlank;
	}

	/**
	 * 处理字符（用于以正常格式显示文本内容）
	 * 
	 * @param pstrContent
	 * @return
	 */
	public static String toHTMLString(String pstrContent) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; pstrContent != null && i < pstrContent.length(); i++) {
			char c = pstrContent.charAt(i);
			if (c == '\'')
				out.append("&#039;");
			else if (c == '\"')
				out.append("&#034;");
			else if (c == '<')
				out.append("&lt;");
			else if (c == '>')
				out.append("&gt;");
			else if (c == '&')
				out.append("&amp;");
			else if (c == ' ')
				out.append("&nbsp;");
			else if (c == '\n')
				out.append("<br>");
			else
				out.append(c);
		}
		return out.toString();
	}

	private static final String SIZE_MIN = "B";
	private static final String SIZE_KB = "KB";
	private static final String SIZE_MB = "MB";
	private static final String SIZE_GB = "GB";

	public static String getShowSize(long fileSize) {
		if (fileSize < 1000) {
			return fileSize + SIZE_MIN;
		} else if (fileSize < 1000 * 1000) {
			return fileSize / 1000 + SIZE_KB;
		} else if (fileSize < 1000 * 1000 * 1000)
			return fileSize / (1000 * 1000) + SIZE_MB;
		else if (fileSize < 1000 * 1000 * 1000 * 1000) {
			return fileSize / (1000 * 1000 * 1000) + SIZE_GB;
		} else {
			return "大于1GB";
		}
	}

	/**
	 * 得到文件大小
	 * 
	 * @param filesize
	 * @return String
	 */
	public static String getFileSize(long filesize) {
		DecimalFormat df = new DecimalFormat("#.0");
		String fileSizeString = "";
		if (filesize < 1024) {
			fileSizeString = df.format((double) filesize) + "B";
		} else if (filesize < 1048576) {
			fileSizeString = df.format((double) filesize / 1024) + "K";
		} else if (filesize < 1073741824) {
			fileSizeString = df.format((double) filesize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) filesize / 1073741824) + "G";
		}
		return fileSizeString;
	}

}