package com.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertUser(UserBean userBean) {
		stmt.update("insert into users(firstname,lastname,email,password,gender,contact,roleid) values(?,?,?,?,?,?,?)",
				userBean.getFirstname(), userBean.getLastname(), userBean.getEmail(), userBean.getPassword(),
				userBean.getGender(), userBean.getContact(), userBean.getRoleid());
	}
}
