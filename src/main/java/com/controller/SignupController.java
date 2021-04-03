package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;

import com.bean.UserBean;

import com.dao.UserDao;

@CrossOrigin
@RestController
public class SignupController 
{
	@Autowired
	UserDao userDao;
	
	@PostMapping("/signup")
	public ResponseBean<UserBean> insertUser(@RequestBody UserBean userBean)
	{
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		userDao.insertUser(userBean);
		responseBean.setData(userBean);
		responseBean.setMsg("User Created..");
		responseBean.setStatus(200);
		return responseBean;
		
	}
	
	@GetMapping("/listusers")
	public ResponseBean<List<UserBean>> getAllUsers()
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> users = userDao.getUsers();
		responseBean.setData(users);
		
		responseBean.setMsg("Users...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listfaculty")
	public ResponseBean<List<UserBean>> getFaculty()
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> faculty = userDao.getFaculty();
		responseBean.setData(faculty);
		responseBean.setMsg("All Faculty...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/liststudent")
	public ResponseBean<List<UserBean>> getStudent()
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> student= userDao.getStudent();
		responseBean.setData(student);
		responseBean.setMsg("Students...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updateuser")
	public ResponseBean<UserBean> updateUser(@RequestBody UserBean userBean)
	{
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		userDao.updateUser(userBean);
		responseBean.setData(userBean);
		responseBean.setMsg("User Updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deleteuser/{userid}")
	public ResponseBean<UserBean> deleteUser(@PathVariable("userid")int userid)
	{
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		UserBean userBean = userDao.deleteUser(userid);
		responseBean.setData(userBean);
		responseBean.setMsg("User Deleted...");
		responseBean.setStatus(200);
		return responseBean;
	}
}
