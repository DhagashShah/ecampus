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

import com.bean.BatchTaskBean;
import com.bean.ResponseBean;
import com.bean.StudentTaskBean;
import com.bean.UserBean;
import com.dao.BatchTaskDao;

@CrossOrigin
@RestController
public class BatchTaskController {

	@Autowired
	BatchTaskDao batchTaskDao;
	
	@PostMapping("/addbatchtask")
	public ResponseBean<BatchTaskBean> addBatchTask(@RequestBody BatchTaskBean batchTaskBean)
	{
		ResponseBean<BatchTaskBean> responseBean = new ResponseBean<>();
		batchTaskDao.addBatchTask(batchTaskBean);
		responseBean.setData(batchTaskBean);
		responseBean.setMsg("Batch Task added...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listbatchtaskbyfaculty/{userid}")
	public ResponseBean<List<BatchTaskBean>> getBatchTaskByFaculty(@PathVariable("userid")int userid)
	{
		ResponseBean<List<BatchTaskBean>> responseBean = new ResponseBean<>();
		List<BatchTaskBean> batchTaskBeans = batchTaskDao.getBatchTaskByFaculty(userid);
		if(batchTaskBeans!=null)
		{
			responseBean.setData(batchTaskBeans);
			responseBean.setMsg("BatchTask By Faculty...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Data not available...");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	@GetMapping("/getbatchtaskbyid/{btid}")
	public ResponseBean<BatchTaskBean> getBatchTaskById(@PathVariable("btid")int btid)
	{
		ResponseBean<BatchTaskBean> responseBean = new ResponseBean<>();
		BatchTaskBean batchTaskBean = batchTaskDao.getBatchTaskById(btid);
		if(batchTaskBean != null)
		{
			responseBean.setData(batchTaskBean);
			responseBean.setMsg("BatchTaskbean fetched...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Invalid...");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	@PutMapping("/updateBatchTask")
	public ResponseBean<BatchTaskBean> updateBatchTask(@RequestBody BatchTaskBean batchTaskBean)
	{
		ResponseBean<BatchTaskBean> responseBean = new ResponseBean<>();
		batchTaskDao.updateBatchTask(batchTaskBean);
		responseBean.setData(batchTaskBean);
		responseBean.setMsg("Batch Task updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deleteBatchTask/{btid}")
	public ResponseBean<BatchTaskBean> deleteBatchTask(@PathVariable("btid")int btid)
	{
		ResponseBean<BatchTaskBean> responseBean = new ResponseBean<>();
		BatchTaskBean batchTaskBean = batchTaskDao.deleteBatchTask(btid);
		if(batchTaskBean!=null)
		{
			responseBean.setData(batchTaskBean);
			responseBean.setMsg("BatchTask deleted...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Invalid...");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	@GetMapping("/getSubmittedTaskByBatch/{btid}")
	public ResponseBean<List<StudentTaskBean>> getSubmittedTask(@PathVariable("btid")int btid)
	{
		ResponseBean<List<StudentTaskBean>> responseBean = new ResponseBean<>();
		List<StudentTaskBean> submittedStudents = batchTaskDao.getSubmittedStudents(btid);
		if(submittedStudents!=null)
		{
			responseBean.setData(submittedStudents);
			responseBean.setMsg("Submitted students fetched...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Invalid..");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	@GetMapping("/getNotSubmittedTaskByBatch/{btid}/{btid}")
	public ResponseBean<List<UserBean>> getNotSubmittedTask(@PathVariable("btid")int btid,@PathVariable("btid")int btid1)
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> stuBeans = batchTaskDao.getNotSubmittedTask(btid,btid1);
		if(stuBeans!=null)
		{
			responseBean.setData(stuBeans);
			responseBean.setMsg("Students Fetched...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Invalid");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	
}
