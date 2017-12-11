package com.zwk.bookstore.user.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.user.domain.User;
import com.zwk.bookstore.user.service.UserService;

public class AdminUserServlet extends HttpServlet {

	private UserService userService = new UserService();
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		User user = null;
		try {
			user = userService.findNameByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("/admin/AdminOrderServlet?method=allOrders").forward(request,response);
	}
}
