package cn.csbit.core.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class NumberUtil {
	
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
		return formatStr(d, "0.##");
	}

	/**
	 * 保留4位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String format4FractionDigits(Double d) {
		return formatStr(d, "0.####");
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
}
