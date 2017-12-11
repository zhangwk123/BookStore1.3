package com.zwk.bookstore.categary.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwk.bookstore.categary.domain.Categary;
import com.zwk.bookstore.categary.service.CategaryService;

import cn.itcast.servlet.BaseServlet;

public class CategaryServlet extends BaseServlet {
	private CategaryService categaryService = new CategaryService();
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categary> categaryList = categaryService.findAll();
		request.setAttribute("categaryList", categaryList);
		return "f:/jsps/left.jsp";
	}
}
