package com.zwk.bookstore.user.service;

import java.sql.SQLException;

import com.zwk.bookstore.user.dao.UserDao;
import com.zwk.bookstore.user.domain.User;

public class UserService {

	private UserDao userDao = new UserDao();

	public void regist(User form) throws UserException, SQLException {
		//用表单的数据到数据库中校检,
		//如果为null,保存到数据库
		//如果不为null,抛出异常"用户名已存在"
		User user = userDao.findByName(form);
		if(user == null)
		{
			userDao.add(form);
		}else{
			throw new UserException("用户名已存在");
		}
	}

	public User login(User form) throws UserException, SQLException {
		User user = userDao.findByName(form);
		if(user==null)
		{
			throw new UserException("用户不存在!");
		}if(!user.getPassword().equals(form.getPassword())){
			throw new UserException("密码错误!");
		}
		return user;
	}

	public User findNameByUid(String uid) throws SQLException {
		
		return userDao.findNameByUid(uid);
	}
	
}
