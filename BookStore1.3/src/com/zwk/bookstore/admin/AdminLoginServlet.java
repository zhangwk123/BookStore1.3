package com.zwk.bookstore.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.user.domain.User;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminLoginServlet extends BaseServlet {

	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User admin = CommonUtils.toBean(request.getParameterMap(), User.class);
		request.getSession().setAttribute("admin", admin);
		if(admin.getUsername().equals("admin") && admin.getPassword().equals("admin")){
			return "r:/adminjsps/admin/index.jsp";
		} else{
			request.setAttribute("msg", "管理员账户或密码错误,请检查后重新登录!");
			return "f:/adminjsps/login.jsp";
		}
		
	}
	
	public String adminquit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "f:/adminjsps/login.jsp";
	}
}
