package com.zwk.bookstore.book.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.book.service.BookService;
import com.zwk.bookstore.categary.domain.Categary;
import com.zwk.bookstore.categary.service.CategaryService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminBookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CategaryService categaryService = new CategaryService();
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.findAll();
		request.setAttribute("bookList", bookList);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		request.setAttribute("book", book);
		List<Categary> categaryList = categaryService.findAll();
		request.setAttribute("categaryList", categaryList);
		return "f:/adminjsps/admin/book/desc.jsp";
	}

	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
	    Categary categary = CommonUtils.toBean(request.getParameterMap(), Categary.class);
	    book.setCategary(categary);
		bookService.edit(book);
		return findAll(request,response);
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String bid = request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request,response);
	}
	public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categary> categaryList = categaryService.findAll();
		request.setAttribute("categaryList", categaryList);
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	public String lookKucun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.findAll();
		request.setAttribute("bookList", bookList);
		return "f:/adminjsps/admin/book/kucunlist.jsp";
	}
	public String loadToKucunDesc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.load(bid);
		request.setAttribute("book", book);
		List<Categary> categaryList = categaryService.findAll();
		request.setAttribute("categaryList", categaryList);
		return "f:/adminjsps/admin/book/kucundesc.jsp";
	}

	public String addKucun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		String kucun = request.getParameter("kucun");
		bookService.updateKucun(kucun,bid);
		return loadToKucunDesc(request,response);
		
		
	}
}
