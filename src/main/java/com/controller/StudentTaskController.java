package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.StudentTaskBean;
import com.dao.StudentTaskDao;

@RestController
public class StudentTaskController {

	@Autowired
	StudentTaskDao studentTaskDao;
	
	@GetMapping("/getSubmittedTask/{batchid}/{userid}")
	public ResponseBean<List<StudentTaskBean>> getBatchTaskByBatch(@PathVariable("batchid")int batchid,@PathVariable("userid")int userid)
	{
		ResponseBean<List<StudentTaskBean>> responseBean = new ResponseBean<>();
		List<StudentTaskBean> studentTaskBeans = studentTaskDao.getBatchTask(batchid,userid);
		if(studentTaskBeans!=null)
		{
			responseBean.setData(studentTaskBeans);
			responseBean.setMsg("Task By Batch");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Task not Found");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	@PostMapping("/addStudentTask")
	public ResponseBean<StudentTaskBean> addStudentTask(@RequestBody StudentTaskBean studentTaskBean)
	{
		ResponseBean<StudentTaskBean> responseBean = new ResponseBean<>();
		studentTaskDao.addStudentTask(studentTaskBean);
		responseBean.setData(studentTaskBean);
		responseBean.setMsg("Task Submitted...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getNotSubmittedTask/{batchid}/{userid}")
	public ResponseBean<List<StudentTaskBean>> getNotSubmittedTask(@PathVariable("batchid")int batchid,@PathVariable("userid")int userid)
	{
		ResponseBean<List<StudentTaskBean>> responseBean = new ResponseBean<>();
		List<StudentTaskBean> studentTaskBeans = studentTaskDao.getNotSubmittedTask(batchid,userid);
		if(studentTaskBeans!=null)
		{
			responseBean.setData(studentTaskBeans);
			responseBean.setMsg("Submitted Task");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Submitted task not found");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	@GetMapping("/getStudentTaskById/{stid}")
	public ResponseBean<List<StudentTaskBean>> getStudentTaskById(@PathVariable("stid")int stid)
	{
		ResponseBean<List<StudentTaskBean>> responseBean = new ResponseBean<>();
		List<StudentTaskBean> studentTaskBean = studentTaskDao.getStudentTaskById(stid);
		responseBean.setData(studentTaskBean);
		responseBean.setMsg("Get StudentTask");
		responseBean.setStatus(200);
		return responseBean;
	}
	
}
