package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;

import com.bean.UserBean;

import com.dao.UserDao;

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
}
