package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.dao.UserDao;

@RestController
public class LoginController 
{
	@Autowired
	UserDao userDao;
	
	@PostMapping("/authenticate")
	public ResponseBean<LoginBean> authenticate(@RequestBody LoginBean loginBean)
	{
		LoginBean loBean = null;
		System.out.println("Email : "+loginBean.getEmail());
		System.out.println("Passowrd : "+loginBean.getPassword());
		loBean = userDao.login(loginBean.getEmail(),loginBean.getPassword());
		ResponseBean<LoginBean> responseBean = new ResponseBean<>();
		if (loBean!=null) 
		{
			
			responseBean.setData(loBean);
			responseBean.setMsg("Login Successfully...");
			responseBean.setStatus(200);
		}
		else 
		{
			responseBean.setMsg("Invalid Credentials...");
			responseBean.setStatus(200);
		}
		
		return responseBean;
	}
}	
