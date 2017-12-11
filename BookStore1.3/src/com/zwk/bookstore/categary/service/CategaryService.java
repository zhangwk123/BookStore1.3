package com.zwk.bookstore.categary.service;

import java.sql.SQLException;
import java.util.List;

import com.zwk.bookstore.book.dao.BookDao;
import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.categary.dao.CategaryDao;
import com.zwk.bookstore.categary.domain.Categary;

public class CategaryService {

	private CategaryDao categaryDao = new CategaryDao();
	private BookDao bookDao = new BookDao();

	public List<Categary> findAll() {
		// TODO Auto-generated method stub
		return categaryDao.findAll();
	}

	public Categary load(String cid) throws SQLException{
		return categaryDao.load(cid);
	}

	public void add(String cname) throws SQLException {
		categaryDao.add(cname);
		
	}

	public void delete(String cid) throws CategaryException, SQLException {
		List<Book> bookList = bookDao.findByCategary2(cid);
		if(bookList.size()!=0){
			throw new CategaryException("该分类下图书不为空,不能删除!");
		}
		categaryDao.delete(cid);
		
	}

	public void mod(String cid,String cname) throws SQLException {
		categaryDao.mod(cid,cname);
		
	}

	public void deleteAll(String cid) {
		categaryDao.deleteAll(cid);
		
	}

	
	
}
