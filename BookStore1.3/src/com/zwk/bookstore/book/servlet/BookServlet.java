package com.zwk.bookstore.book.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.book.domain.PageBean;
import com.zwk.bookstore.book.service.BookService;
import com.zwk.bookstore.categary.domain.Categary;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.findAll();
		request.setAttribute("bookListAll", bookList);
		return "f:/jsps/book/list.jsp";
		
	}
	
	public String findAllPageBean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int pc = getPc(request);
		//System.out.println(pc);
		int ps = 4;
		PageBean pb = bookService.findAll(pc,ps);
		request.setAttribute("pb", pb);
		request.setAttribute("bookListAll", pb.getBeanList());
		return "f:/jsps/book/list.jsp";
		
	}
	
	private int getPc(HttpServletRequest request) {
		String pc = request.getParameter("pc");
		if(pc==null||pc.trim().isEmpty())
			return 1;
		return Integer.parseInt(pc);
	}

	public String findByCategary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		List<Book> bookList = bookService.findByCategary(cid,cname);
		request.setAttribute("bookList", bookList);
		return "f:/jsps/book/list.jsp";
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = bookService.load(request.getParameter("bid"));
		request.setAttribute("book",book);
		return "f:/jsps/book/desc.jsp";
	}
	
	public String searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.searchByName(request.getParameter("bname"));
		request.setAttribute("bookListAllByName",bookList);
		return "f:/jsps/book/list.jsp";
	}
}
