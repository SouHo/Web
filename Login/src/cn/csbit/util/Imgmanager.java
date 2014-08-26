package cn.csbit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Imgmanager {

	//图片转化为byte[]
		public static String getImageStr(String imgPath) throws UnsupportedEncodingException{
			File f=new File(imgPath);
			byte[] logoImgStream = null ;
			if(f.exists()){
				logoImgStream=new byte[Long.valueOf(f.length()).intValue()];
				try {
					InputStream in = new FileInputStream(f);
					in.read(logoImgStream);
					in.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return new String(Base64.encodeBase64(logoImgStream), "UTF-8").trim();
		}
}
