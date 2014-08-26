package cn.csbit.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public final class Helpers {
	public static String md5Encode(String str) {
		MessageDigest md5;
		String newstr = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			newstr = Base64
					.encodeBase64String(md5.digest(str.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("未找到MD5加密算法！", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("未找到MD5加密算法！", e);
		}
		return newstr.trim();
	}

	// int 和字符数组之间的转换
	public static byte[] IntToBytes(int val) {
		byte[] bs = new byte[4];
		java.nio.ByteBuffer bf = java.nio.ByteBuffer.wrap(bs);
		bf.putInt(val);
		return bs;
	}

	public static int BytesToInt(byte[] val) {
		java.nio.ByteBuffer bf = java.nio.ByteBuffer.wrap(val);
		return bf.getInt();
	}

	public static byte[] ShortToBytes(short val) {
		byte[] ba = new byte[2];
		ba[0] = (byte) ((val & 0xFF00) >> 8);
		ba[1] = (byte) (val & 0xFF);
		return ba;
	}

	public static short BytesToShort(byte[] val) {
		java.nio.ByteBuffer bf = java.nio.ByteBuffer.wrap(val);
		return bf.getShort();
	}

	// /日期的转换，好像apache有，先放在这个，待清理
	// 24小时制
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将字符串转换为日期 格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static java.util.Date strToDate(String str) {
		return strToDate(str, DEFAULT_FORMAT);
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
			// e.printStackTrace();
			throw new RuntimeException("日期格式错误！日期格式应为：" + pattern);
		}
		return date;
	}

	/**
	 * 将日期转换为字符串形式 默认格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr(java.util.Date date) {
		assert (date != null);

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

	// 得到对象的字符值
	public static String getObjStr(Object o) {
		return (o != null ? o.toString() : "");
	}

	/**
	 * 保留n位小数
	 * 
	 * @param d
	 * @param pattern
	 *            "0.000" 三位小数
	 * @return
	 */
	public static String formatStr(Double d, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(d);
	}

	/**
	 * 保留2位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String format2FractionDigits(Double d) {
		return formatStr(d, "0.00");
	}

	/**
	 * 保留4位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String format4FractionDigits(Double d) {
		return formatStr(d, "0.0000");
	}

	/**
	 * 将数字格式化成123，321，256.00，并保留n位小数
	 */

	public static String formatThousands(Double object, int n) {
		NumberFormat nFormat = NumberFormat.getInstance();
		nFormat.setMaximumFractionDigits(n);
		return nFormat.format(object);
	}

	/**
	 * 将数字格式化成123，321，256.00，并保留2位小数
	 */
	public static String format2Thousands(Double d) {
		return formatThousands(d, 2);
	}

	/**
	 * 将数字格式化成123，321，256.0000，并保留4位小数
	 */
	public static String format4Thousands(Double object) {
		return formatThousands(object, 4);
	}

	/**
	 * 将object类型转换成整形
	 * 
	 * @param object
	 * @param needClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toNumber(Object object, Class<T> needClass) {
		if (object == null)
			return null;

		else if ("".equals(object)
				&& (needClass == Integer.class || needClass == Long.class
						|| needClass == Short.class || needClass == Float.class
						|| needClass == Double.class || needClass == Byte.class))
			return null;

		else if (object.getClass() == needClass)
			return (T) object;

		return StringUtil.toNumber(object.toString(), needClass);
	}

	/**
	 * 生成随机数
	 * 
	 * @length随机数的长度
	 * */
	public static String getRadomNumber(int length) {
		Double r = Math.random() * Math.pow(10, length);
		String ra = String.valueOf(r.longValue());
		String radom = ra;
		if (ra.length() < length) {
			for (int i = 0; i < length - ra.length(); i++) {
				radom = "0" + radom;
			}
		}
		return radom;
	}

	/**
	 * 根据参数自动生成文件名称，统一文件格式
	 * 
	 * @param header
	 *            文件名称 类别
	 * @param date
	 *            文件标示 日期
	 * @param type
	 *            文件后缀 类型
	 * @return String
	 * */
	public static String getFileName(String header, Date date, String type) {
		if (header == null)
			header = "csbit";
		if (date == null)
			date = new Date();
		if (type == null)
			type = "txt";
		StringBuilder fileNameString = new StringBuilder();
		fileNameString.append(header);
		fileNameString.append("_");
		fileNameString.append(dateToStr(date, "yyyyMMddHHmmss"));
		fileNameString.append("_");
		fileNameString.append(getRadomNumber(4));
		fileNameString.append(".");
		fileNameString.append(type);
		return fileNameString.toString();
	}

	//JDK7已经直接支持了,javapractices.com
	public static Boolean copyFile(String sFName, String tName) {
		File aSourceFile = new File(sFName);
		File aTargetFile = new File(tName);
		
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			try {
				byte[] bucket = new byte[32 * 1024];
				inStream = new BufferedInputStream(new FileInputStream(
						aSourceFile));
				outStream = new BufferedOutputStream(new FileOutputStream(
						aTargetFile, false));
				int bytesRead = 0;
				while (bytesRead != -1) {
					bytesRead = inStream.read(bucket); // -1, 0, or more
					if (bytesRead > 0) {
						outStream.write(bucket, 0, bytesRead);
					}
				}
				
				return true;
			} finally {
				if (inStream != null)
					inStream.close();
				if (outStream != null)
					outStream.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
}
