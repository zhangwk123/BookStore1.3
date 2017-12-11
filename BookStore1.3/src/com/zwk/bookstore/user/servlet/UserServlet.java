package com.zwk.bookstore.user.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.cart.domain.Cart;
import com.zwk.bookstore.user.domain.User;
import com.zwk.bookstore.user.service.UserException;
import com.zwk.bookstore.user.service.UserService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {

	private UserService userService = new UserService();
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获取表单
		//用户校检
		//如果存在,抛出异常
		//如果不存在,注册成功
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
	    form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
	    form.setState(true);
	    form.setEmail("email");
		Map<String,String> errors = new HashMap<String,String>();
		String username = form.getUsername();
		String password = form.getPassword();
		if(username==null||username.trim().isEmpty())
		{
			errors.put("username","用户名不能为空" );
		}else if(username.length()<3||username.length()>10)
		{
			errors.put("username","用户名长度必须3-10之间");
		}
		if(password==null||password.trim().isEmpty())
		{
			errors.put("password","密码不能为空" );
		}else if(password.length()<3||password.length()>10)
		{
			errors.put("password","密码长度必须3-10之间");
		}
		if(errors.size()>0)
		{
			request.setAttribute("errors", errors);
			return "f:/jsps/user/regist.jsp";
		}
		
		try{
		    userService.regist(form);
		    request.setAttribute("msg", "恭喜您注册成功");
			return "f:/jsps/msg.jsp";
		}catch(UserException e)
		{
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/user/regist.jsp";
		}	
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//从页面获取数据
		//然后到数据库中查询
		//如果有,允许登录,保存用户到session
		//如果没有,抛出异常
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String,String> errors = new HashMap<String,String>();
		String username = form.getUsername();
		String password = form.getPassword();
		if(username==null||username.trim().isEmpty())
		{
			errors.put("username","用户名不能为空" );
		}else if(username.length()<3||username.length()>10)
		{
			errors.put("username","用户名长度必须3-10之间");
		}
		if(password==null||password.trim().isEmpty())
		{
			errors.put("password","密码不能为空" );
		}else if(password.length()<3||password.length()>10)
		{
			errors.put("password","密码长度必须3-10之间");
		}
		if(errors.size()>0)
		{
			request.setAttribute("errors", errors);
			return "f:/jsps/user/login.jsp";
		}
		try{
		  User user = userService.login(form);
		  request.getSession().setAttribute("session_user", user);
		  Cart cart = new Cart();
		  request.getSession().setAttribute("cart", cart);
		  return "f:/index.jsp";
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/user/login.jsp";
		}
		
		
	}
	
	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "f:/index.jsp";
	}
}
