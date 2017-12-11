package com.zwk.bookstore.categary.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zwk.bookstore.categary.domain.Categary;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class CategaryDao {
	private QueryRunner qr = new TxQueryRunner();

	public List<Categary> findAll() {
		String sql = "select * from categary";
		try {
			return qr.query(sql,new BeanListHandler<Categary>(Categary.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Categary load(String cid) throws SQLException {
		String sql = "select * from categary where cid = ?";
		return qr.query(sql, new BeanHandler<Categary>(Categary.class),cid);
	}

	public void add(String cname) throws SQLException {
		String sql = "insert into categary values(?,?)";
		qr.update(sql,CommonUtils.uuid(),cname);
		
	}

	public void delete(String cid) throws SQLException {
		String sql = "delete from categary where cid = ?";
		qr.update(sql,cid);
		
	}

	public void mod(String cid,String cname) throws SQLException {
		String sql = "update categary set cname = ? where cid = ?";
		qr.update(sql,cname,cid);
		
	}

	public void deleteAll(String cid) {
		String sql1 = "delete from book where cid = ?";
		String sql2 = "delete from categary where cid = ?";
		try {
			qr.update(sql1,cid);
			qr.update(sql2,cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

}
