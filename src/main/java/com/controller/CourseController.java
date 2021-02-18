package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CourseBean;
import com.bean.ResponseBean;
import com.dao.CourseDao;

@RestController
public class CourseController 
{
	@Autowired
	CourseDao courseDao;
	
	@PostMapping("/addcourse")
	public ResponseBean<CourseBean> addCourse(@RequestBody CourseBean courseBean)
	{
		ResponseBean<CourseBean> responseBean = new ResponseBean<>();
		courseDao.insertCourse(courseBean);
		responseBean.setData(courseBean);
		responseBean.setMsg("Recod inserted...");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listcourse")
	public ResponseBean<List<CourseBean>> listCourse()
	{
		ResponseBean<List<CourseBean>> responseBean = new ResponseBean<>();
		List<CourseBean> courses=courseDao.allCourse();
		responseBean.setData(courses);
		responseBean.setMsg("All Courses...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updatecourse")
	public ResponseBean<CourseBean> updateCourse(@RequestBody CourseBean courseBean)
	{
		ResponseBean<CourseBean> responseBean = new ResponseBean<>();
		courseDao.updateCourse(courseBean);
		responseBean.setData(courseBean);
		responseBean.setMsg("Record updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deletecourse/{courseid}")
	public ResponseBean<CourseBean> deleteCourse(@PathVariable("courseid")int courseid)
	{
		ResponseBean<CourseBean> responseBean = new ResponseBean<>();
		CourseBean courseBean = courseDao.deleteCourse(courseid);
		if (courseBean!=null) 
		{
			responseBean.setData(courseBean);
			responseBean.setMsg("Record deleted...");
			responseBean.setStatus(200);
		} 
		else 
		{
			responseBean.setMsg("Record not found...");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
}
