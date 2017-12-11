package com.zwk.bookstore.order.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.book.dao.BookDao;
import com.zwk.bookstore.book.service.BookService;
import com.zwk.bookstore.cart.domain.Cart;
import com.zwk.bookstore.cart.domain.CartItem;
import com.zwk.bookstore.order.domain.Order;
import com.zwk.bookstore.order.domain.OrderItem;
import com.zwk.bookstore.order.service.OrderException;
import com.zwk.bookstore.order.service.OrderService;
import com.zwk.bookstore.user.domain.User;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	private static Order order = null;
	private OrderService orderService = new OrderService();
	public String add(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
	   //创建 order
		order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setState(1);
		User owner = (User) request.getSession().getAttribute("session_user");
		order.setOwner(owner);
		order.setTotal(cart.getTotal());
		
		//创建orderItemList
		Collection<CartItem> cartItems = cart.getCartItems();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cartItems)
		{
			OrderItem orderItem =  new OrderItem();
			orderItem.setBook(cartItem.getBook());
			orderItem.setCount(cartItem.getCount());
			orderItem.setIid(CommonUtils.uuid());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		
		cart.clear();
		//添加订单之前清除购物车
		try {
			orderService.add(order);
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg2.jsp";
		}
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
		
	}

	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//从session中获取user
		//通过uid查询用户所有订单
		User user = (User)request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user);
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
	}

	public String cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, OrderException {
		String oid = request.getParameter("oid");
		orderService.cancel(oid);
		//System.out.println(order);
		orderService.renew(order);  //每次取消订单恢复库存
		return myOrders(request,response);
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
	}
	
	
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String oid = request.getParameter("oid");
		try{
		   orderService.confirm(oid);
		   request.setAttribute("msg", "交易完成,期待下次光临!");
		}catch(OrderException e)
		{
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
}
