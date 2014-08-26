package cn.csbit.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.InvocationUtils;
import cn.csbit.model.User;

//获取程序全局信息的辅助类
public final class Global {
	private final static String CUE_USER = "currentUser"; // 授权处理中，放入会话中的特殊键

	// 获取当前会话用户
	public static User getCurrentUser() {
		HttpServletRequest request = InvocationUtils.getCurrentThreadRequest();
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(CUE_USER);
		return currentUser;
	}

	// 获取当前会话用户名
	public static String getCurrentUserName() {
		HttpServletRequest request = InvocationUtils.getCurrentThreadRequest();
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(CUE_USER);
		return currentUser == null ? "缺省" : currentUser.getName();//anonymous
	}
	
	
	
	//Web根路径 部署时的根路径，WEB-INF的父路径
	public static String WebRoot(){
		HttpServletRequest request = InvocationUtils.getCurrentThreadRequest();
		return request.getSession().getServletContext().getRealPath("/");
	}
}
