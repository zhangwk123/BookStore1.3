package com.zwk.bookstore.order.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.order.domain.Order;
import com.zwk.bookstore.order.service.OrderService;

import cn.itcast.servlet.BaseServlet;

public class AdminOrderServlet extends BaseServlet {

	private OrderService orderService = new OrderService();
	
	public String allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Order> orderList = orderService.allOrders();
		request.setAttribute("orderList", orderList);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	public String deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String oid = request.getParameter("oid");
		orderService.deleteAll(oid);
		return allOrders(request,response);
	}
}
