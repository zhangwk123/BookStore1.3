package com.zwk.bookstore.book.service;

import java.sql.SQLException;
import java.util.List;

import com.zwk.bookstore.book.dao.BookDao;
import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.book.domain.PageBean;
import com.zwk.bookstore.order.domain.Order;
import com.zwk.bookstore.order.domain.OrderItem;

public class BookService {

	private BookDao bookDao = new BookDao();

	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}
	
	public /*List<Book>*/PageBean findAll(int pc,int ps) throws SQLException {
		// TODO Auto-generated method stub
		return bookDao.findAll(pc,ps);
	}

	public List<Book> findByCategary(String cid,String cname) {
		return bookDao.findByCategary(cid,cname);
	}

	public Book load(String bid) {
		return bookDao.load(bid);
	}

	public void delete(String bid) throws SQLException {
		bookDao.delete(bid);
		
	}

	public void add(Book book) {
		bookDao.add(book);
	}

	public void edit(Book book) {
		bookDao.edit(book);
		
	}

	public void updateKucun(String kucun,String bid) {
		bookDao.updateKucun(kucun,bid);
		
	}

	public List<Book> searchByName(String bname) {
		return bookDao.searchByName(bname);
	}
}
