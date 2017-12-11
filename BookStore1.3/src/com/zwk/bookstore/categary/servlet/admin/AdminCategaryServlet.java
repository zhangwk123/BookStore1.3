package com.zwk.bookstore.categary.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.categary.domain.Categary;
import com.zwk.bookstore.categary.service.CategaryException;
import com.zwk.bookstore.categary.service.CategaryService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategaryServlet extends BaseServlet {
	private CategaryService categaryService = new CategaryService();
	private Categary categary = new Categary();
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categary> categaryList = categaryService.findAll();
		request.setAttribute("categaryList", categaryList);
		return "f:/adminjsps/admin/category/list.jsp";
	}
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cid = request.getParameter("cid");
		Categary categary = categaryService.load(cid);
		request.setAttribute("categary", categary);
		return "f:/adminjsps/admin/category/mod.jsp";
	}

	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cname = request.getParameter("cname");
		categaryService.add(cname);
		return findAll(request,response);
	}

	public String mod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		categaryService.mod(cid,cname);
		return findAll(request,response);
	}
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CategaryException, SQLException {
		String cid = request.getParameter("cid");
		try{
		   categaryService.delete(cid);
		}catch(CategaryException e)
		{
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/admin/msg.jsp";
		}
		return findAll(request,response);
	}
	
	public String deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		categaryService.deleteAll(cid);
		return findAll(request,response);
	}
}
