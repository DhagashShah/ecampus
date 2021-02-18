package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	public List<UserBean> getUsers() {
		List<UserBean> users = null;
		users = stmt.query("select * from users", BeanPropertyRowMapper.newInstance(UserBean.class));
		return users;
	}

	public void updateUser(UserBean userBean) {
		stmt.update(
				"update users set firstname=?,lastname=?,email=?,password=?,gender=?,contact=?,roleid=? where userid=?",
				userBean.getFirstname(), userBean.getLastname(), userBean.getEmail(), userBean.getPassword(),
				userBean.getGender(), userBean.getContact(), userBean.getRoleid(), userBean.getUserid());
	}

	public UserBean getUserById(int userid) {
		UserBean userBean = null;
		userBean = stmt.queryForObject("select * from users where userid=?",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { userid });
		return userBean;
	}

	public UserBean deleteUser(int userid) 
	{
		UserBean userBean = null;
		try 
		{
			userBean= getUserById(userid);
			if (userBean!=null) 
			{
				stmt.update("delete from users where userid=?",userid);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return userBean;
	}
}
