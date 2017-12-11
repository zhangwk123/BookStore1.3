package com.zwk.bookstore.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.book.domain.PageBean;
import com.zwk.bookstore.categary.domain.Categary;
import com.zwk.bookstore.order.domain.Order;
import com.zwk.bookstore.order.domain.OrderItem;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	
	private QueryRunner qr = new TxQueryRunner();

	public List<Book> findAll() {
		String sql = "select * from book";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	 //分页
	public PageBean findAll(int pc,int ps) throws SQLException{
		PageBean pb = new PageBean();
		pb.setPc(pc);
		pb.setPs(ps);
		String sql = "select * from book limit ?,?";
		List<Book> beanList = qr.query(sql,new BeanListHandler<Book>(Book.class),(pc-1)*ps,ps);
		pb.setBeanList(beanList);
		sql = "select count(*) from book";
		Number num = (Number) qr.query(sql, new ScalarHandler());
		int tr = num.intValue();
		pb.setTr(tr);
		return pb;
	}

	public List<Book> findByCategary2(String cid) {
		String sql = "select * from book where cid = ?";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////
	public List<Book> findByCategary(String cid,String cname) {
		String sql = "select * from book b,categary c where b.cid = c.cid and cname = ?";
		List<Book> bookList = null;
	    try {
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),cname);
			bookList = toBookList(mapList);

			
		} catch (SQLException e) {
		}
		return bookList;
	
	}

	private List<Book> toBookList(List<Map<String, Object>> mapList) {
	     List<Book> bookList = new ArrayList<Book>();
	     for(Map<String,Object> map : mapList)
	     {
	    	 Book book = toBook(map);
	    	 bookList.add(book);
	     }
	     return bookList;
   }

	private Book toBook(Map<String, Object> map) {
		Categary categary = CommonUtils.toBean(map, Categary.class);
		Book book = CommonUtils.toBean(map,Book.class);
		book.setCategary(categary);
		return book;
	}
///////////////////////////////////////////////////////////////////////////////////////////////

	public Book load(String bid) {
			String sql = "select * from book where bid = ?";
			try {
				Map<String,Object> map = qr.query(sql,new MapHandler(),bid);
				Categary categary = CommonUtils.toBean(map, Categary.class);
				Book book = CommonUtils.toBean(map,Book.class);
				book.setCategary(categary);
				return book;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	public void delete(String bid) throws SQLException {
		String sql = "delete from book where bid = ?";
		qr.update(sql,bid);
		
	}

	public void add(Book book) {
		String sql = "insert into book values(?,?,?,?,?,3,?,false)";
		Object[] params = {book.getBid(),book.getBname(),book.getPrice(),
		                   book.getAuthor(),book.getImage(),book.getCategary().getCid()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void edit(Book book) {
		String sql = "update book set bname=?,price=?,author=?,cid=? where bid = ?";
		Object[] params = {book.getBname(),book.getPrice(),book.getAuthor(),book.getCategary().getCid(),book.getBid()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateBookCount(Order order) throws SQLException {
		String sql = "update book set kucun=kucun-? where bid = ?";
		
		List<OrderItem> items = order.getOrderItemList();
		Object[][] params = new Object[items.size()][];
		int bookcount = 0;
		String bid  =null;
		for(int i=0;i<items.size();i++){
			OrderItem item = items.get(i);
			params[i] = new Object[]{item.getCount(),item.getBook().getBid()};
			//params[i][1] = item.getBook().getBid();//bid
			bid = item.getBook().getBid();
			bookcount = item.getCount();
		}
		String sql2 = "select kucun from book where bid = ?";
		int _kc = (Integer) qr.query(sql2,new ScalarHandler(),bid);
		int kc = _kc - bookcount;
		if(kc<0){throw new SQLException();}
		qr.batch(sql, params);
	}
	

	public void updateKucun(String kucun,String bid) {
		String sql = "update book set kucun=? where bid = ?";
		Object[] params = {kucun,bid};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<Book> searchByName(String bname) {
		String sql = "select * from book where bname like ?";
		try {
			return qr.query(sql,new BeanListHandler<Book>(Book.class),"%"+bname+"%");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void addkucun(OrderItem orderItem, int bookcount) throws SQLException {
		String sql = "update book set kucun=kucun+? where bid = ?";
		qr.update(sql,bookcount,orderItem.getBook().getBid());
		
	}

	public void renew(Order order) {
		String sql = "update book set kucun=kucun+? where bid = ?";
		Object[][] params = new Object[order.getOrderItemList().size()][];
		for(int i=0;i<order.getOrderItemList().size();i++)
		{
			OrderItem orderItem = order.getOrderItemList().get(i);
			params[i] = new Object[]{orderItem.getCount(),orderItem.getBook().getBid()};
		}
		try {
			qr.batch(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
