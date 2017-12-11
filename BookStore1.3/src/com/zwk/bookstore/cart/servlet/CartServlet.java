package com.zwk.bookstore.cart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.book.service.BookService;
import com.zwk.bookstore.cart.domain.Cart;
import com.zwk.bookstore.cart.domain.CartItem;

import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	private Cart cart = new Cart();
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//得到车
    	//得到条目(书对象+数量)
    	//登录成功后在session中添加一Cart对象
    	Cart cart = (Cart) request.getSession().getAttribute("cart");
    	String bid = request.getParameter("bid");
    	Book book = new BookService().load(bid);
    	int count = Integer.parseInt(request.getParameter("count"));
    	CartItem cartItem = new CartItem();
    	cartItem.setBook(book);
    	cartItem.setCount(count);
    	cart.add(cartItem);
    	return "f:/jsps/cart/list.jsp";
    }
    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	Cart cart = (Cart)request.getSession().getAttribute("cart");
    	cart.clear();
    	return "f:/jsps/cart/list.jsp";
    }
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Cart cart = (Cart)request.getSession().getAttribute("cart");
    	String bid = request.getParameter("bid");
    	cart.delete(bid);
    	return "f:/jsps/cart/list.jsp";
    }
}
