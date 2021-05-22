package com.biyeseng.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 共用工具类
 */
public class CommonUtil {
	/**
	 * 获取当前日期
	 * @return 日期信息
	 */
	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return  sdf.format(new Date());
	}
	
	/**
	 * HTML转换服务
	 * @param sourcestr
	 * @return
	 */
	public static String TextToHtml(String sourcestr) {
		int strlen;
		String restring = "", destr = "";
		strlen = sourcestr.length();
		for (int i = 0; i < strlen; i++) {
			char ch = sourcestr.charAt(i);
			switch (ch) {
			case '<':
				destr = "<";
				break;
			case '>':
				destr = ">";
				break;
			case '\"':
				destr = "\"";
				break;
			case '&':
				destr = "&";
				break;
			case 13:
				destr = "<br>";
				break;
			case 32:
				destr = "&nbsp;";
				break;
			default:
				destr = "" + ch;
				break;
			}
			restring = restring + destr;
		}
		return "" + restring;
	}
}
