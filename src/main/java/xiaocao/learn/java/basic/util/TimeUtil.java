package xiaocao.learn.java.basic.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	
	/**
	 * 年月日
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * 时分秒
	 */
	public static final String HH_MM_SS = "HH:mm:ss";
	
	/**
	 * 年月日时分
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	
	/**
	 * 年月日时分秒
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 年月日时分秒毫秒
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static Calendar toCalendar(String dateStr)  {
		return toCalendar(dateStr, YYYY_MM_DD);
	}
	
	public static String toString(Calendar cal) {
		return toString(cal, YYYY_MM_DD);
	}
	
	public static Timestamp toTimestamp(String str) {
		return toTimestamp(str, YYYY_MM_DD);
	}
	
	public static String toString(Timestamp stamp) {
		return toString(stamp, YYYY_MM_DD);
	}
	
	public static Calendar toCalendar(String dateStr, String pattern)  {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		try {
			cal.setTime(format.parse(dateStr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;
	}
	
	public static String toString(Calendar cal, String pattern)  {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}
	
	public static String toString(long ms, String pattern)  {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(ms);
	}
	
	public static String toString(Date date, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		return toString(cal, pattern);
	}
	
	public static String toString(Timestamp stamp, String pattern) {
		return toString((Date) stamp, pattern);
	}
	
	public static Timestamp toTimestamp(String str, String pattern) {
		Calendar cal = toCalendar(str, pattern);
		Timestamp stamp = new Timestamp(cal.getTimeInMillis());
		return stamp;
	}
	

}
