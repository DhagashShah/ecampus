package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SignupBean;

@Repository
public class SignupDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertUser(SignupBean signupBean) {
		stmt.update("insert into users(firstname,lastname,email,password,gender,contact,roleid) values(?,?,?,?,?,?,?)",
				signupBean.getFirstname(), signupBean.getLastname(), signupBean.getEmail(), signupBean.getPassword(),
				signupBean.getGender(), signupBean.getContact(), signupBean.getRoleid());
	}
}
