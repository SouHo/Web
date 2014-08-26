package cn.csbit.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.csbit.util.ConstantUtil;
/**
 * SimpleDateFormat 中日期说明：
 * y年  M月  d日  h时（1～12） H时（0～23） m分 s秒 S毫秒 E星期 D天（0～366） F一个月中的第几个星期几
 * w 一年中的第几个星期 W一个月中的第几个星期 a上午/下午标识符 k时(1~24) K时(0~11) z时区
 * @author zh
 *
 */
public final class DateUtil {

		// 24小时制
		private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
		
		/**
		 * 获取当前时间和日期
		 * @return
		 */
		public static final String getCurrentHms(){
			Calendar calendar = Calendar.getInstance();
			return dateToStrHms(calendar.getTime());
		}
		public static final String getCurrentHmsS(){
			Calendar calendar = Calendar.getInstance();
			return dateToStr(calendar.getTime(), "yyyy-MM-dd HH:mm:ss.SSS");
		}
		public static final String getCurrentyMd(){
			Calendar calendar = Calendar.getInstance();
			return dateToStr(calendar.getTime(), "yyyy-MM-dd");
		}
		
		/**
		 * 获取n天前的时间
		 */
		public static final String getPreNDateHms(int n){
			Calendar calendar = Calendar.getInstance();
			long millis = calendar.getTimeInMillis();
			calendar.setTimeInMillis(millis - n*ConstantUtil.dayMillis);
			return dateToStrHms(calendar.getTime());
		}
		public static final String getPreNDateyMd(int n){
			Calendar calendar = Calendar.getInstance();
			long millis = calendar.getTimeInMillis();
			calendar.setTimeInMillis(millis - n*ConstantUtil.dayMillis);
			return dateToStr(calendar.getTime(),"yyyy-MM-dd");
		}
		
		/**
		 * 将字符串转换为日期 格式为：yyyy-MM-dd HH:mm:ss
		 * 
		 * @param str
		 * @return
		 */
		public static java.util.Date strToDateHms(String str) {
			return strToDate(str, DEFAULT_FORMAT);
		}

		/**
		 * 将日期转换为字符串形式 
		 * 
		 * @param date
		 * @return y-M-d H:m:s
		 */
		public static String dateToStrHms(java.util.Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			return sdf.format(date);
		}

		/**
		 * 将日期转换为字符串形式,需要指定日期格式
		 * 
		 * @param date
		 * @param format
		 *            格式
		 * @return
		 */
		public static String dateToStr(java.util.Date date, String format) {
			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.applyPattern(format);
				return sdf.format(date);
			} else {
				return "";
			}
		}
		
		/**
		 * 将字符串转换为日期,需要制定格式
		 * 
		 * @param str
		 * @param pattern
		 *            日期格式
		 * @return
		 */
		public static java.util.Date strToDate(String str, String pattern) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			java.util.Date date = null;
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				throw new RuntimeException("日期格式错误！日期格式应为：" + pattern);
			}
			return date;
		}
		
		/**
		 * 毫秒转时长 天时分秒
		 * @param Long 毫秒
		 * @param Boolean 是否显示秒
		 * @return String *天 *时 *分 /*秒
		 * */
		public static String formatDuring(long mss,boolean isSec) {  
		    long days = mss / (1000 * 60 * 60 * 24);  
		    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
		    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
		    long seconds = (mss % (1000 * 60)) / 1000;  
		    return days + "天 " + hours + "时 " + minutes + "分 "  
		            +(isSec?seconds + "秒 ":"");  
		}
}
