package com.controller;

import java.util.ArrayList;
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

import com.bean.BatchBean;
import com.bean.ResponseBean;
import com.bean.StudentBatchBean;
import com.bean.StudentCourseBean;
import com.bean.UserBean;
import com.dao.BatchDao;

@CrossOrigin
@RestController
public class BatchController 
{
	@Autowired
	BatchDao batchDao;
	
	@PostMapping("/addbatch")
	public ResponseBean<BatchBean> insertBatch(@RequestBody BatchBean batchBean)
	{
		System.out.println("userid : "+batchBean.getUserid());
		ResponseBean<BatchBean> responseBean= new ResponseBean<>();
		batchDao.insertBatch(batchBean);
		responseBean.setData(batchBean);
		responseBean.setMsg("Batch Added..");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deletebatch/{batchid}")
	public ResponseBean<BatchBean> deleteBatch(@PathVariable("batchid")int batchid)
	{
		ResponseBean<BatchBean> responseBean= new ResponseBean<>();
		BatchBean batchBean=batchDao.deleteBatch(batchid);
		responseBean.setData(batchBean);
		responseBean.setMsg("Batch Deleted...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getbatchbyid/{batchid}")
	public ResponseBean<BatchBean> getBatchById(@PathVariable("batchid")int batchid)
	{
		ResponseBean<BatchBean> responseBean = new ResponseBean<>();
		BatchBean batchBean = batchDao.getBatchById(batchid);
		responseBean.setData(batchBean);
		responseBean.setMsg("Batch fetched...");
		responseBean.setStatus(200);
		return responseBean;
		
	}
	
	@GetMapping("/allbatch")
	public ResponseBean<List<BatchBean>> getAllBatch()
	{
		ResponseBean<List<BatchBean>> responseBean = new ResponseBean<>();
		List<BatchBean> batches=batchDao.getBatches();
		responseBean.setData(batches);
		responseBean.setMsg("All Batches..");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getstudentbycourse/{courseid}")
	public ResponseBean<List<UserBean>> getStudentByCourse(@PathVariable("courseid")  int courseid)
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> studentCourseBeans = batchDao.getStudentByCourse(courseid);
		responseBean.setData(studentCourseBeans);
		responseBean.setMsg("Studentwise course....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	
	@GetMapping("/getstudentbybatch/{batchid}")
	public ResponseBean<List<UserBean>> getStudentByBatch(@PathVariable("batchid") int batchid)
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> students = batchDao.getStudentByBatch(batchid);
		responseBean.setData(students);
		responseBean.setMsg("Batchwise Students....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	
	
	
	@PutMapping("/updatebatch")
	public ResponseBean<BatchBean> updateBatch(BatchBean batchBean)
	{
		ResponseBean<BatchBean> responseBean = new ResponseBean<>();
		batchDao.updateBatch(batchBean);
		responseBean.setData(batchBean);
		responseBean.setMsg("Batch updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PostMapping("/assignbatch")
	public ResponseBean<StudentBatchBean> addStudentBatch(@RequestBody StudentBatchBean studentBatchBean)
	{
		ResponseBean<StudentBatchBean> responseBean = new ResponseBean<>();
		batchDao.addStudentToBatch(studentBatchBean);
		responseBean.setData(studentBatchBean);
		responseBean.setMsg("Student Added in respective batch...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	
}
