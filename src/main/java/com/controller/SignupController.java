package com.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.SignupBean;
import com.dao.SignupDao;

@RestController
public class SignupController 
{
	@Autowired
	SignupDao signupDao;
	
	@PostMapping("/signup")
	public ResponseBean<SignupBean> insertUser(@RequestBody SignupBean signupBean)
	{
		ResponseBean<SignupBean> responseBean = new ResponseBean<>();
		signupDao.insertUser(signupBean);
		responseBean.setData(signupBean);
		responseBean.setMsg("User Created..");
		responseBean.setStatus(200);
		return responseBean;
		
	}
}
