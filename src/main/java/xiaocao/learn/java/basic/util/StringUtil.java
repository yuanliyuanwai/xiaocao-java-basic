package xiaocao.learn.java.basic.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 字符串是否为空或者为空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNil(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * 如果为null则返回空否则对字符串进行trim操作
	 * @param str
	 * @return
	 */
	public static String trimString(String str) {
		if(isNil(str)) return "";
		return str.trim();
	}
	
	/**
	 * 得到字节长度:双字节字符2个字节，字母一个字节
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str) {
		int length = 0;
		if (isNil(str)) {
			return length;
		}
		Pattern p = Pattern.compile("[^\\x00-\\xff]");
		for (int i = 0; i < str.length(); i++) {
			Matcher m = p.matcher(String.valueOf(str.charAt(i)));
			if (m.matches())
				length += 2;
			else {
				length++;
			}
		}
		return length;
	}
	
	/**
	 * 获取每个字符和它所在字符串中的字节长度的对应关系
	 * @param str
	 * @return
	 */
	public static Map<Integer, String> getByteLengthMap(String str) {
		Map<Integer, String> lengthMap = new HashMap<Integer, String>();
		int length = 0;
		if (isNil(str)) {
			lengthMap.put(Integer.valueOf(0), "");
		}
		Pattern p = Pattern.compile("[^\\x00-\\xff]");
		for (int i = 0; i < str.length(); i++) {
			Matcher m = p.matcher(String.valueOf(str.charAt(i)));
			if (m.matches())
				length += 2;
			else {
				length++;
			}
			lengthMap.put(Integer.valueOf(length), str.substring(0, i + 1));
		}
		return lengthMap;
	}
	
	/**
     * 将一个String类型的数组转换成一个int类型的数组
     * 
     * @param strs
     * @return
     */
    public static int[] convert(String[] strs) {
        int[] arr = new int[strs.length];
        int index = 0;
        for (String str : strs) {
            arr[index++] = Integer.parseInt(str);
        }
        return arr;
    }

}
