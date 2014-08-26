package cn.csbit.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import cn.csbit.util.ConstantUtil;
/**
 * 执行系统脚本的工具类
 * @author 张浩
 *
 */
public final class ScriptUtil {
	static Logger logger = Logger.getLogger(ScriptUtil.class);
	///运行系统Shell脚本或命令行,返回结果串
	public static final String RunCmd(String[] shell){
		StringBuilder suc = new StringBuilder();
		try {		
			Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
			//OutputStream可对shell实现输入
			Process p;
			p = run.exec(shell);
			
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// 获得命令执行后在控制台的输出信息
				suc.append(lineStr);
				suc.append("\n");
				logger.info(lineStr);
			}
			
			// 检查命令是否执行失败。
			p.waitFor();
			//p.exitValue(); // p.exitValue()==0表示正常结束，1：非正常结束
			p.destroy();

			inBr.close();
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("执行脚本错误！"+e.getLocalizedMessage(), e);
		}
		
		return suc.toString();
	}

	/** 运行Shell脚本或命令行
	 * @param relShellParams 脚本的名称: WEB-INF/classes目录下 命令参数：[1~..]
	 * @param result 脚本输出结果
	 * @return true:成功
	 * @throws Exception 
	 */
	public static boolean RunAppCmd(String[] relShellParams,
			StringBuilder result){
		boolean suc = false;
		if(result==null)result = new StringBuilder();
		try {
			String root = ConstantUtil.CLASSPATH + relShellParams[0];
			relShellParams[0]= root;
			File curDir = new File(root).getParentFile(); //当前路径和shell的目录一致
			
			Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
			//OutputStream可对shell实现输入
			Process p = run.exec(relShellParams, null, curDir);

			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// 获得命令执行后在控制台的输出信息
				result.append(lineStr);
				result.append("\n");
				logger.info(lineStr);
			}
			
			// 检查命令是否执行失败。
			p.waitFor();
			suc = p.exitValue() == 0; // p.exitValue()==0表示正常结束，1：非正常结束
			p.destroy();

			inBr.close();
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("执行脚本错误！"+e.getLocalizedMessage(), e);
		}
		
		return suc;
	}
	
	public static void RunAppCmd(String relShellParams) {
		try {
			String shell = ConstantUtil.CLASSPATH + relShellParams;
			File curDir = new File(shell).getParentFile(); //当前路径和shell的目录一致
			
			Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
			Process p = run.exec(shell, null, curDir);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// 获得命令执行后在控制台的输出信息
				logger.info(lineStr);
			}
			logger.info(p.toString());
		} catch (Exception e) {
			throw new RuntimeException("执行脚本错误！"+e.getLocalizedMessage(), e);
		}
	}
}
