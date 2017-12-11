package com.zwk.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zwk.bookstore.book.domain.Book;
import com.zwk.bookstore.order.domain.Order;
import com.zwk.bookstore.order.domain.OrderItem;
import com.zwk.bookstore.user.domain.User;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {

	private QueryRunner qr = new TxQueryRunner();
	
	//添加订单
	public void addOrder(Order order)
	{
		String sql = "insert into orders values(?,?,?,?,?,?)";
		Object[] params = {order.getOid(),new Timestamp(order.getOrdertime().getTime()),order.getTotal(),order.getState(),order.getOwner().getUid(),order.getAddress()};
	    try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加条目
	public void addOrderItemList(List<OrderItem> orderItemList)
	{
		String sql = "insert into orderitem values(?,?,?,?,?)";
		//把list转成一个二维数组,为了批处理
		Object[][] params = new Object[orderItemList.size()][];
/*		for(OrderItem orderitem : orderItemList)
		{	
		}*/
		//为单个条目赋值
		for(int i=0;i<orderItemList.size();i++)
		{
			OrderItem orderItem = orderItemList.get(i);
			params[i] = new Object[]{orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getOrder().getOid(),orderItem.getBook().getBid()};
		}
		try {
			qr.batch(sql, params);//批处理,把多个条目(条目List)一起添加到数据库中
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> findByUid(User user) throws SQLException {
		String sql = "select * from orders where uid = ?";
		List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid());
		//遍历所有的订单,为每个订单加载所有的订单条目
		for(Order order : orderList)
		{
			loadOrderItemList(order);
		}
		return orderList;
	}

	private void loadOrderItemList(Order order) throws SQLException {
		//因为select * from orderitem 这样没有 图书的书名和单价信息,所以需要联合查找
		//联合查找一条记录包含多个表,所以不能用beanlistHandler,改用mapListHandler
		String sql = "select * from orderitem i,book b where i.bid=b.bid and oid = ?";
		List<Map<String,Object>> mapList = qr.query(sql,new MapListHandler(),order.getOid());
		//我们需要OrderItemList,所以要把mapList转变为OrderItemList
	    List<OrderItem> orderItemList = toOrderItemList(mapList);
	    order.setOrderItemList(orderItemList);
	}

	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		//要把mapList转换为orderItemList,可以遍历mapList,
		//然后把map转换为orderItem,
		//然后把orderItem添加到orderItemList,返回orderItemList
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList)
		{
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	private OrderItem toOrderItem(Map<String, Object> map) {
		//要把map转换为orderItem,那么要用到CommonUtils.toBean到OrderItem和Book
		//然后把需要book添加到orderItem,返回orderItem,这样上面的orderItemList中就含有book信息了
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	public void cancel(String oid) throws SQLException {
		String sql1 = "delete from orderitem where oid = ?";
		String sql2 = "delete from orders where oid = ?";
		qr.update(sql1,oid);
		qr.update(sql2,oid);
	}

	public Order load(String oid) {
		String sql = "select * from orders where oid = ?";
		Order order = null;
		try {
			order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			loadOrderItemList(order);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return order;
	}

	public int getStateByOid(String oid) throws SQLException {
		String sql = "select state from orders where oid = ?";
		return (Integer)qr.query(sql, new ScalarHandler(),oid);
	}
	

	public void updateState(String oid, int state) throws SQLException {
		String sql = "update orders set state = ? where oid = ?";
		qr.update(sql,state,oid);
		
	}

	public List<Order> findAllOrders() throws SQLException {

		String sql = "SELECT * FROM orders o,orderitem i WHERE o.oid = i.oid;";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
      // order.setOrderItemList(orderItemList);
		List<Order> orderList = new ArrayList<Order>();
		for(Map<String,Object> map : mapList)
		{
			Order order = toOrder(map);
			orderList.add(order);
		}
		return orderList;
	}

	private Order toOrder(Map<String, Object> map) {
		Order order = CommonUtils.toBean(map, Order.class);
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		User user = CommonUtils.toBean(map, User.class);
		order.setOrderItem(orderItem);
		order.setOwner(user);
		return order;
	}

	public void deleteAll(String oid) throws SQLException {
		String sql1 = "delete from orderitem where oid = ?";
		String sql2 = "delete from orders where oid = ?";
		qr.update(sql1,oid);
		qr.update(sql2,oid);
		
	}
	
	
}
