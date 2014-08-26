package cn.csbit.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public final class StringUtil {
	private static final Logger logger = Logger.getLogger(StringUtil.class);
	@SuppressWarnings("serial")
	private static final Map<Class<?>, Method> methods = new HashMap<Class<?>, Method>(){
		{
			try{
				put(Long.class, Long.class.getDeclaredMethod("valueOf", String.class));
				put(long.class, Long.class.getDeclaredMethod("parseLong", String.class));
				put(Double.class, Double.class.getDeclaredMethod("valueOf", String.class));
				put(double.class, Double.class.getDeclaredMethod("parseDouble", String.class));
				put(Float.class, Float.class.getDeclaredMethod("valueOf", String.class));
				put(float.class, Float.class.getDeclaredMethod("parseFloat", String.class));
				put(Integer.class, Integer.class.getDeclaredMethod("valueOf", String.class));
				put(int.class, Integer.class.getDeclaredMethod("parseInt", String.class));
				put(Short.class, Short.class.getDeclaredMethod("valueOf", String.class));
				put(short.class, Short.class.getDeclaredMethod("parseShort", String.class));
				put(Byte.class, Byte.class.getDeclaredMethod("valueOf", String.class));
				put(byte.class, Byte.class.getDeclaredMethod("parseByte", String.class));
				put(Date.class, Date.class.getDeclaredMethod("valueOf", String.class));
			}catch(Exception e){
				logger.error(e);
				throw new RuntimeException(e);
			}
		}
	};
	
	public static final boolean isEmpty(String str){
		return str==null || str.length()==0;
	}
	/**
	 * 将字符串转换成需要的数据类型
	 * @param str
	 * @param needClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T toNumber(String str, Class<T> needClass){
		if(needClass==String.class) return (T)str;
		Method method = methods.get(needClass);
		if(method==null) return null;
		try {
			return (T) method.invoke(null, str);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static final String toString(Object object){
		return object==null ? "":object.toString();
	}
	
	/**
	 * 针对ascii中不可显示字符进行处理为 空格
	 * */
	public static final String togoRString(String str,StringBuilder sb,String sv){
		if(str!=null){
			sb = new StringBuilder();
			char[] c = str.toCharArray();
			for(int i=0;i<c.length;i++){
				sb.append((int)c[i]).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			String[] scs = sb.toString().split(",");
			sb = new StringBuilder();
			for(int i=0;i<scs.length;i++){
				if(Integer.parseInt(scs[i], 16)<32){
					sv="32"; ///int  32  对应 ascii的 空格 " "
				}else{
					sv = scs[i];
				}
				sb.append((char)Integer.parseInt(sv));
			}
			return sb.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * IP 字符串 转 Long
	 * @param String
	 * @return Long
	 * */
	public static final Long ipToLong(String ipStr){
		Long [] ip = new Long[4];
		int i1 = ipStr.indexOf(".");
		int i2 = ipStr.indexOf(".",i1+1);
		int i3 = ipStr.indexOf(".",i2+1);
		ip[0] = Long.parseLong(ipStr.substring(0,i1));
		ip[1] = Long.parseLong(ipStr.substring(i1+1,i2));
		ip[2] = Long.parseLong(ipStr.substring(i2+1,i3));
		ip[3] = Long.parseLong(ipStr.substring(i3+1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
	
	/**
	 * IP Long 转 字符串
	 * @param Long
	 * @return String
	 * */
	public static final String ipToString(Long ipL){
		StringBuilder sb = new StringBuilder("");
		sb.append(String.valueOf(ipL >>> 24)).append(".");
		sb.append(String.valueOf((ipL & 0x00FFFFFF) >>> 16)).append(".");
		sb.append(String.valueOf((ipL & 0x0000FFFF) >>> 8)).append(".");
		sb.append(String.valueOf(ipL & 0x000000FF));
		return sb.toString();
	}
}
