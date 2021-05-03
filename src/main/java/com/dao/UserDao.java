package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CountBean;
import com.bean.LoginBean;
import com.bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertUser(UserBean userBean) {
		stmt.update(
				"insert into users(firstname,lastname,email,password,gender,contact,roleid,active) values(?,?,?,?,?,?,?,?)",
				userBean.getFirstname(), userBean.getLastname(), userBean.getEmail(), userBean.getPassword(),
				userBean.getGender(), userBean.getContact(), userBean.getRoleid(), userBean.isActive());
	}

	public List<UserBean> getUsers() {
		List<UserBean> users = null;
		users = stmt.query(
				"select users.userid,users.firstname,users.lastname,users.email,users.gender,users.contact,role.rolename from users inner join role on users.roleid=role.roleid",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return users;
	}

	public void updateUser(UserBean userBean) {
		stmt.update(
				"update users set firstname=?,lastname=?,email=?,password=?,gender=?,contact=?,roleid=?,active=? where userid=?",
				userBean.getFirstname(), userBean.getLastname(), userBean.getEmail(), userBean.getPassword(),
				userBean.getGender(), userBean.getContact(), userBean.getRoleid(), userBean.isActive(),
				userBean.getUserid());
	}

	public UserBean getUserById(int userid) {
		UserBean userBean = null;
		userBean = stmt.queryForObject("select * from users where userid=?",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { userid });
		return userBean;
	}

	public UserBean deleteUser(int userid) {
		UserBean userBean = null;
		try {
			userBean = getUserById(userid);
			if (userBean != null) {
				stmt.update("delete from users where userid=?", userid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}

	public UserBean login(String email, String password) {
		UserBean users = null;
		try {
			users = stmt.queryForObject("select * from users where active=true and  email=? and password=?",
					BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { email, password });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<UserBean> getFaculty() {
		List<UserBean> faculty = null;
		faculty = stmt.query(
				"SELECT users.userid,users.firstname,users.lastname,users.email,users.gender,users.contact,users.active,role.rolename FROM users INNER JOIN role ON users.roleid=role.roleid and users.active=true and role.roleid = 2",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return faculty;
	}

	public List<UserBean> getStudent() {
		List<UserBean> student = null;
		student = stmt.query(
				"SELECT users.userid,users.firstname,users.lastname,users.email,users.gender,users.contact,role.rolename FROM users INNER JOIN role ON users.roleid=role.roleid and role.roleid = 3 and users.active=true order by users.userid asc",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return student;
	}

	public List<UserBean> getBlockedStudent() {
		List<UserBean> student = null;
		student = stmt.query(
				"select users.*,role.rolename from users,role where users.roleid=role.roleid and active=false and role.roleid=3",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return student;

	}

	public List<UserBean> getBlockedFaculty() 
	{
		List<UserBean> faculty = null;
		faculty = stmt.query(
				"select users.*,role.rolename from users,role where users.roleid=role.roleid and active=false and role.roleid=2",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return faculty;
	}

	public CountBean getTotalFaculty() 
	{
		CountBean c  = stmt.queryForObject("select count(*) from users where roleid=2",BeanPropertyRowMapper.newInstance(CountBean.class));
		return c;
	}

	public CountBean getTotalStudent() 
	{
		CountBean c  = stmt.queryForObject("select count(*) from users where roleid=3",BeanPropertyRowMapper.newInstance(CountBean.class));
		return c;
	}

	public UserBean getUserByEmail(String email) 
	{
		UserBean userBean = null;
		userBean = stmt.queryForObject("select * from users where email=?",BeanPropertyRowMapper.newInstance(UserBean.class),new Object[] {email});
		return userBean;
	}

	public void updatePassword(UserBean dbUser) 
	{
		stmt.update("update users set password=? where email=?",dbUser.getPassword(),dbUser.getEmail());
	}
}
