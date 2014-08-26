package cn.csbit.controllers;

import net.paoding.rose.web.ControllerErrorHandler;
import net.paoding.rose.web.Invocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandler implements ControllerErrorHandler {
	private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	// 通用onError方法，处理其他onError无法处理的异常
	@Override
	public Object onError(Invocation inv, Throwable ex) {
		inv.getResponse().setStatus(500);
		//inv.getResponse().getOutputStream().write(Byte.ex.getMessage())
		inv.getRequest().setAttribute("messager", ex.getLocalizedMessage());
		logger.error(ex.getStackTrace().toString());
		return "error/500.jsp";
	}
}
