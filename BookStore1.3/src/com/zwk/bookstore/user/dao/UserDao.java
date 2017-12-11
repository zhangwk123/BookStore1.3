package com.zwk.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zwk.bookstore.user.domain.User;

import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {

	private QueryRunner qr = new TxQueryRunner();
	public User findByName(User form) throws SQLException {
		String sql = "select * from tb_user where username = ?";
		return qr.query(sql,new BeanHandler<User>(User.class),form.getUsername());
	}

	public void add(User form) {
		String sql = "insert into tb_user values(?,?,?,?,?,?)";
		//System.out.println(form.getUid()+","+form.getUsername()+","+form.getPassword()+","+form.getEmail()+","+form.getCode()+","+form.isState());
		Object[] params = {form.getUid(),form.getUsername(),form.getPassword(),form.getEmail(),form.getCode(),form.isState()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public User findNameByUid(String uid) throws SQLException {
		String sql = "select username from tb_user where uid = ?";
		return qr.query(sql, new BeanHandler<User>(User.class),uid);
	}

}
