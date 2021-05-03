package com.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CountBean;
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
	
	@GetMapping("/getfacultybyid/{userid}")
	public ResponseBean<UserBean> getFacultyById(@PathVariable("userid")int userid)
	{
		ResponseBean<UserBean> responseBean =  new ResponseBean<>();
		UserBean user = userDao.getUserById(userid);
		if(user!=null)
		{
			responseBean.setData(user);
			responseBean.setMsg("Faculty fetched....");
			responseBean.setStatus(200);
		}
		else 
		{
			responseBean.setMsg("Invalid credentials...");
		}
		return responseBean;
	}
	
	@GetMapping("/getstudentbyid/{userid}")
	public ResponseBean<UserBean> getStudentById(@PathVariable("userid")int userid)
	{
		ResponseBean<UserBean> responseBean = new ResponseBean<UserBean>();
		UserBean student = userDao.getUserById(userid);
		if(student!=null)
		{
			responseBean.setData(student);
			responseBean.setMsg("Student...");
			responseBean.setStatus(200);
		}
		else 
		{
			responseBean.setMsg("Invalid Credentials...");
			responseBean.setStatus(200);
		}
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
	
	@GetMapping("/getblockedstudents")
	public ResponseBean<List<UserBean>> getBlockedStudents()
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> students = userDao.getBlockedStudent();
		responseBean.setData(students);
		responseBean.setMsg("Blocked Students....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getblockedfaculty")
	public ResponseBean<List<UserBean>> getBlockedFaculty()
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> faculty = userDao.getBlockedFaculty();
		responseBean.setData(faculty);
		responseBean.setMsg("Blocked Faculty");
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
	
	@GetMapping("/totalfaculty")
	public ResponseBean<CountBean> getTotalFaculty()
	{
		ResponseBean<CountBean> responseBean = new ResponseBean<>();
		CountBean countBean = userDao.getTotalFaculty();
		responseBean.setData(countBean);
		responseBean.setMsg("Total Faculty");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/totalstudent")
	public ResponseBean<CountBean> getTotalStudent()
	{
		ResponseBean<CountBean> responseBean = new ResponseBean<>();
		CountBean countBean = userDao.getTotalStudent();
		responseBean.setData(countBean);
		responseBean.setMsg("Total Student");
		responseBean.setStatus(200);
		return responseBean;
	}
}
