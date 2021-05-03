package com.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.TaskBean;
import com.dao.TaskDao;

@CrossOrigin
@RestController
public class TaskController {

	@Autowired
	TaskDao taskDao;
	
	@PostMapping("/addtask")
	public ResponseBean<TaskBean> addTask(@RequestBody TaskBean taskBean)
	{
		ResponseBean<TaskBean> responseBean = new ResponseBean<>();
		taskDao.addTask(taskBean);
		responseBean.setData(taskBean);
		responseBean.setMsg("Task Added...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getNotAssignedTaskByUser/{userid}")
	public ResponseBean<List<TaskBean>> getTaskByUser(@PathVariable("userid")int userid)
	{
		ResponseBean<List<TaskBean>> responseBean = new ResponseBean<>();
		List<TaskBean> taskBeans = taskDao.getTaskByUser(userid);
		responseBean.setData(taskBeans);
		responseBean.setMsg("Task by faculty...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getAssignedTaskByUser/{userid}")
	public ResponseBean<List<TaskBean>> getAssignedTaskByUser(@PathVariable("userid")int userid)
	{
		ResponseBean<List<TaskBean>> responseBean = new ResponseBean<>();
		List<TaskBean> taskBeans = taskDao.getAssignedTaskByUser(userid);
		responseBean.setData(taskBeans);
		responseBean.setMsg("Task by faculty...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getAllTask/{userid}")
	public ResponseBean<List<TaskBean>> getAllTaskByUser(@PathVariable("userid")int userid)
	{
		ResponseBean<List<TaskBean>> responseBean = new ResponseBean<>();
		List<TaskBean> taskBeans = taskDao.getAllTaskByUser(userid);
		responseBean.setData(taskBeans);
		responseBean.setMsg("Task by faculty...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getTaskById/{taskid}")
	public ResponseBean<TaskBean> getTaskById(@PathVariable("taskid")int taskid)
	{
		ResponseBean<TaskBean> responseBean = new ResponseBean<>();
		TaskBean taskBean = taskDao.getTaskById(taskid);
		if(taskBean!=null)
		{
			responseBean.setData(taskBean);
			responseBean.setMsg("Task Fetched...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Task not fetched...");
			responseBean.setStatus(200);
		}
		
		return responseBean;
	}
	
	@PutMapping("/updateTask")
	public ResponseBean<TaskBean> updateTask(@RequestBody TaskBean taskBean)
	{
		ResponseBean<TaskBean> responseBean = new ResponseBean<>();
		taskDao.updateTask(taskBean);
		responseBean.setData(taskBean);
		responseBean.setMsg("Task updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deleteTask/{taskid}")
	public ResponseBean<TaskBean> deleteTask(@PathVariable("taskid")int taskid)
	{
		ResponseBean<TaskBean> responseBean = new ResponseBean<>();
		TaskBean taskBean = taskDao.deleteTask(taskid);
		if(taskBean!=null)
		{
			responseBean.setData(taskBean);
			responseBean.setMsg("Task deleted...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Task not deleted...");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
}
