/**
 * 
 */
package hong.bus.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSON;

import hong.bus.pojo.Station;

/**
 * @author Abbey
 *
 */
public class SystemCommonUtil {
	
	public static String getServerDate() {
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return date;
	}
	
	private static boolean isGBK(char c){
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
			||ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
			||ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
			||ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  // GENERAL_PUNCTUATION 判断中文的“号  
			||ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION     // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号  
			||ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号  
		)
		return true;
		return false;
	}
	
	/**
	 * 判断字符串是否有汉字
	 * @param str
	 * @return true:有/false:没有
	 */
	public static boolean isGBK(String str){
		char[] ch =  str.toCharArray();
		for (char c : ch) {
			if(isGBK(c))
				return true;
		}
		return false;
	}
	
	/**
     * 该函数判断一个字符串是否包含标点符号（中文英文标点符号）。
     * 原理是原字符串做一次清洗，清洗掉所有标点符号。
     * 此时，如果原字符串包含标点符号，那么清洗后的长度和原字符串长度不同。返回true。
     * 如果原字符串未包含标点符号，则清洗后长度不变。返回false。
     * @param s
     * @return
     */
    public static boolean isPunctuation(String str) {
        boolean b = false;
 
        String tmp = str;
        tmp = tmp.replaceAll("(\\p{P}", "");
        if (str.length() != tmp.length()) {
            b = true;
        }
 
        return b;
    }
    
    /**
     * 将json字符串转化成List<T>
     * @param str json字符串
     * @param clazz	转化成的bean的class :=> T.class
     * @return
     */
    public static <T> List<T> jsonStringToList(String str, Class<T> clazz){
    	String temp = null;
		try {
			temp = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
		try {
		String jsonString = JSON.toJSONString(temp);
		List<T> list = JSON.parseArray(temp, clazz);
		return list;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
}
