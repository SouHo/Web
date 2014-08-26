package cn.csbit.controllers;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.paoding.rose.web.var.Model;

import org.springframework.beans.factory.annotation.Autowired;

import cn.csbit.core.hibernate.BaseService;
import cn.csbit.model.User;
import cn.csbit.service.LoginService;
import cn.csbit.util.ConstantUtil;

@Path("")
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private BaseService baseService;
	
	
	@Get("")
	public String index(){
		return "login.jsp";
	}
	
	
	@Post("login")//登录处理
	public String login(User user, HttpSession session, Model model) {
			User currentUser = this.loginService.checkExist(user.getName(), user.getPassword());
			session.setAttribute("currentUser", currentUser);//保存当前用户
			return ConstantUtil.SUCCESS;		
	}
	
	
}
