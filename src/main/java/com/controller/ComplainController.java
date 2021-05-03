package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ComplainBean;
import com.bean.ResponseBean;
import com.dao.ComplainDao;

@CrossOrigin
@RestController
public class ComplainController 
{
	@Autowired
	ComplainDao complainDao;
	
	@PostMapping("/addcomplain")
	public ResponseBean<ComplainBean> addComplain(@RequestBody ComplainBean complainBean)
	{
		ResponseBean<ComplainBean> responseBean = new ResponseBean<>();
		complainDao.insertComplain(complainBean);
		responseBean.setData(complainBean);
		responseBean.setMsg("Complain added...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getcomplainbystudent/{userid}")
	public ResponseBean<List<ComplainBean>> getComplainByUser(@PathVariable("userid")int userid)
	{
		ResponseBean<List<ComplainBean>> responseBean = new ResponseBean<>();
		List<ComplainBean> comBeans = complainDao.getComplainByStudent(userid);
		if(comBeans!=null)
		{
			responseBean.setData(comBeans);
			responseBean.setMsg("Complaints....");
			responseBean.setStatus(200);
		}
		else 
		{
			responseBean.setMsg("No Complaints...");
		}
		return responseBean;
	}
	
	@GetMapping("/getallcomplain")
	public ResponseBean<List<ComplainBean>> getAllComplain()
	{
		ResponseBean<List<ComplainBean>> responseBean = new ResponseBean<>();
		List<ComplainBean> comList = complainDao.getAllComplain();
		responseBean.setData(comList);
		responseBean.setMsg("All Complaints...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getresolvecomplain/{userid}")
	public ResponseBean<List<ComplainBean>> getComplain(@PathVariable("userid")int userid)
	{
		ResponseBean<List<ComplainBean>> responseBean = new ResponseBean<>();
		List<ComplainBean> complainBeans  = complainDao.getResolvedComplain(userid);
		responseBean.setData(complainBeans);
		responseBean.setMsg("All Resolved Complaints...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getcomplainbyid/{cid}")
	public ResponseBean<ComplainBean> getComplainById(@PathVariable("cid")int cid)
	{
		ResponseBean<ComplainBean> responseBean = new ResponseBean<>();
		ComplainBean complainBean = complainDao.getComplainById(cid);
		responseBean.setData(complainBean);
		responseBean.setMsg("Complain received...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updatecomplain")
	public ResponseBean<ComplainBean> updateComplain(@RequestBody ComplainBean complainBean)
	{
		ResponseBean<ComplainBean> responseBean = new ResponseBean<>();
		complainDao.updateComplain(complainBean);
		responseBean.setData(complainBean);
		responseBean.setMsg("Complain updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
}
