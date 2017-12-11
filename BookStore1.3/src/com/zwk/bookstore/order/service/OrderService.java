package com.zwk.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transaction;

import com.zwk.bookstore.book.dao.BookDao;
import com.zwk.bookstore.order.dao.OrderDao;
import com.zwk.bookstore.order.domain.Order;
import com.zwk.bookstore.user.domain.User;

import cn.itcast.jdbc.JdbcUtils;

public class OrderService {

	private OrderDao orderDao = new OrderDao();
	private BookDao bookDao = new BookDao();
	public void add(Order order) throws OrderException
	{
		try{
			//因为对多项纪录操作,所以要用事务
			//事务开始
			//事务提交
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);
			orderDao.addOrderItemList(order.getOrderItemList());
			bookDao.updateBookCount(order); //bookcount:一个订单条目的书的数量
			JdbcUtils.commitTransaction(); 
		}catch(SQLException e)
		{
			//出现异常事务回滚
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				
			}
			throw new OrderException("库存不足,请联系管理员添加");
		}
	}
	public List<Order> myOrders(User user) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.findByUid(user);
	}
	public void cancel(String oid) throws SQLException {
		orderDao.cancel(oid);
		
	}
	public Order load(String oid) {
		// TODO Auto-generated method stub
		return orderDao.load(oid);
	}
	public void confirm(String oid) throws OrderException, SQLException {
		int state = orderDao.getStateByOid(oid);
		if(state !=3 )
		{
			throw new OrderException("订单没付款,确认失败!");
		}
		orderDao.updateState(oid,4);
		
	}
	public List<Order> allOrders() throws SQLException {
		
		return orderDao.findAllOrders();
	}
	public void deleteAll(String oid) throws SQLException {
		orderDao.deleteAll(oid);
		
	}
	public void renew(Order order) {
		bookDao.renew(order);
		
	}
}
