package com.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AttendanceBean;
import com.bean.BatchBean;
import com.bean.CountBean;
import com.bean.ResponseBean;
import com.bean.StudentBatchBean;
import com.bean.StudentCourseBean;
import com.bean.UserBean;
import com.dao.BatchDao;
import com.dao.UserDao;

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
	
	@DeleteMapping("/deletebatchstudent/{userid}")
	public ResponseBean<UserBean> deletebatchstudent(@PathVariable("userid")int userid)
	{
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		UserBean userBean = batchDao.deleteBatchStudent(userid);
		if(userBean!=null)
		{
			responseBean.setData(userBean);
			responseBean.setMsg("Student deleted from batch....");
			responseBean.setStatus(200);
		}
		else 
		{
			responseBean.setMsg("Student not found....");
		}
		
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
	
	@GetMapping("/getbatchbystudent/{userid}")
	public ResponseBean<List<BatchBean>> getBatchByStudent(@PathVariable("userid")int userid)
	{
		ResponseBean<List<BatchBean>> responseBean = new ResponseBean<>();
		List<BatchBean> batch = batchDao.getBatchByStudent(userid);
		responseBean.setData(batch);
		responseBean.setMsg("Batch...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	
	
	
	@PutMapping("/updatebatch")
	public ResponseBean<BatchBean> updateBatch(@RequestBody BatchBean batchBean)
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
	
	@PostMapping("/addattendance")
	public ResponseBean<AttendanceBean> addStudentAttendance(@RequestBody AttendanceBean attendanceBean)
	{
		ResponseBean<AttendanceBean> responseBean = new ResponseBean<>();
		batchDao.addStudentAttendance(attendanceBean);
		responseBean.setData(attendanceBean);
		responseBean.setMsg("Attendance added...");
		return responseBean;
	}
	
	@GetMapping("/getAttendanceDateByBatch/{batchid}")
	public ResponseBean<List<AttendanceBean>> getAttendanceByDate(@PathVariable("batchid")int batchid)
	{
		ResponseBean<List<AttendanceBean>> responseBean = new ResponseBean<>();
	    List<AttendanceBean> dates = batchDao.getDateByBatch(batchid);
		responseBean.setData(dates);
		responseBean.setMsg("Dates Fetched...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getStudentNotInAnyBatch/{courseid}/{batchid}")
	public ResponseBean<List<UserBean>> getStudent(@PathVariable("courseid") int courseid,@PathVariable("batchid")int  batchid)
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> students = batchDao.getStudentNotInBatch(courseid,batchid);
		responseBean.setData(students);
		responseBean.setMsg("Students not assigned any batch");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PostMapping("/getStudentByDate")
	public ResponseBean<List<UserBean>> getStudentByDate(@RequestBody UserBean userBean)
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> students = batchDao.getStudentByDate(userBean.getAdate(),userBean.getBatchid());
		System.out.println(userBean.getAdate());
		responseBean.setData(students);
		responseBean.setMsg("Students By Date");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PostMapping("/getAbsentStudentByDate")
	public ResponseBean<List<UserBean>> getAbsentStudent(@RequestBody UserBean userBean)
	{
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		List<UserBean> students = batchDao.getAbsentStudents(userBean.getBatchid(),userBean.getAdate(),userBean.getAdate());
		responseBean.setData(students);
		responseBean.setMsg("Absent Students...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getbatchbyfaculty/{userid}")
	public ResponseBean<List<BatchBean>> getBatchByFaculty(@PathVariable("userid")int userid)
	{
		ResponseBean<List<BatchBean>> responseBean = new ResponseBean<>();
		List<BatchBean> bathes = batchDao.getBatchByFaculty(userid);
		responseBean.setData(bathes);
		responseBean.setMsg("Batches....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/totalbatch")
	public ResponseBean<CountBean> getTotalBatch()
	{
		ResponseBean<CountBean> responseBean = new ResponseBean<>();
		CountBean countBean = batchDao.getTotalBatch();
		responseBean.setData(countBean);
		responseBean.setMsg("Total Batch");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getTotalSessions/{batchid}")
	public ResponseBean<CountBean> getTotalSession(@PathVariable("batchid")int  batchid)
	{
		ResponseBean<CountBean> responseBean = new ResponseBean<>();
		CountBean countBean = batchDao.getTotalSession(batchid);
		if(countBean!=null)
		{
			responseBean.setData(countBean);
			responseBean.setMsg("Total Session By Batch");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Count Not Available");
			responseBean.setStatus(200);
		}
		return responseBean;
	}
	
	
	
}
