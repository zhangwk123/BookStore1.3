package com.zwk.bookstore.book.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.book.service.BookService;
import com.zwk.bookstore.categary.domain.Categary;

import cn.itcast.commons.CommonUtils;

public class AdminAddBookServlet extends HttpServlet {

	private BookService bookService = new BookService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CommonUtils.toBean只能用于非文件项的表单,所以这里不能这样用
		//Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		
		//带有文件的要用工厂传递参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//普通表单项保存到book.class
		Map<String,Object> map = new HashMap<String,Object>();
		for(FileItem fileItem : fileItemList)
		{
			if(fileItem.isFormField())
			{
				map.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
			}
		}
		Book book = CommonUtils.toBean(map, Book.class);
		//需要把Map中的cid封装到Categary对象中,再把Categary对象封装到Book
		Categary categary = CommonUtils.toBean(map, Categary.class);
		book.setCategary(categary);
		book.setBid(CommonUtils.uuid());
		
		//保存上传文件
		//设置保存路径
		//得到文件名称
		//使用文件名和保存路径创建目标文件
		//保存上传文件到目标文件位置
		String savePath = this.getServletContext().getRealPath("/book_img");
		String filename = CommonUtils.uuid()+"_"+fileItemList.get(1).getName().substring(fileItemList.get(1).getName().length()-2);
		File destFile = new File(savePath,filename);
		try {
			fileItemList.get(1).write(destFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setImage("book_img/"+filename);
		
		bookService.add(book);
		request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request,response);
	}
}
