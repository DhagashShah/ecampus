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

import com.bean.DoubtBean;
import com.bean.ResponseBean;
import com.dao.DoubtDao;

@CrossOrigin
@RestController
public class DoubtController {

	@Autowired
	DoubtDao doubtDao;
	
	@PostMapping("/adddoubt")
	public ResponseBean<DoubtBean> addDoubt(@RequestBody DoubtBean doubtBean)
	{
		ResponseBean<DoubtBean> responseBean = new ResponseBean<>();
		doubtDao.addDoubt(doubtBean);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubt Added...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updateNotAcceptedDoubt")
	public ResponseBean<DoubtBean> updateDoubt2(@RequestBody DoubtBean doubtBean)
	{
		ResponseBean<DoubtBean> responseBean = new ResponseBean<>();
		doubtDao.updateNotAcceptedDoubt(doubtBean);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubt Updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/alldoubt/{sid}")
	public ResponseBean<List<DoubtBean>> allDoubt(@PathVariable("sid")int sid)
	{
		ResponseBean<List<DoubtBean>> responseBean = new ResponseBean<>();
		List<DoubtBean> doubts = doubtDao.getDoubts(sid);
		responseBean.setData(doubts);
		responseBean.setMsg("All Doubts...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getdoubtbycourse/{courseid}/{userid}")
	public ResponseBean<List<DoubtBean>> getDoubtByCourse(@PathVariable("courseid")int courseid,@PathVariable("userid")int userid)
	{
		ResponseBean<List<DoubtBean>> responseBean = new ResponseBean<>();
		List<DoubtBean> doubts = doubtDao.getDoubtByCourse(courseid,userid);
		responseBean.setData(doubts);
		responseBean.setMsg("Doubt By Course");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	
	
	@GetMapping("/getDoubtById/{did}")
	public ResponseBean<DoubtBean> getDoubtById(@PathVariable("did")int did)
	{
		ResponseBean<DoubtBean> responseBean = new ResponseBean<>();
		DoubtBean doubtBean = doubtDao.getDoubtById(did);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubt By ID");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getNotAceeptedDoubtByStudent/{sid}")
	public ResponseBean<List<DoubtBean>> getNotAcceptedDoubtById(@PathVariable("sid")int sid)
	{
		ResponseBean<List<DoubtBean>> responseBean = new ResponseBean<>();
		List<DoubtBean> doubtBean = doubtDao.getNotAcceptedDoubtById(sid);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubt By ID");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getAcceptedDoubts/{cid}")
	public ResponseBean<List<DoubtBean>> getAcceptedDoubt(@PathVariable("cid")int cid)
	{
		ResponseBean<List<DoubtBean>> responseBean = new ResponseBean<>();
		List<DoubtBean> doubtBean = doubtDao.getAcceptedDoubts(cid);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubts...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updateDoubt")
	public ResponseBean<DoubtBean> updateDoubt(@RequestBody DoubtBean doubtBean)
	{
		ResponseBean<DoubtBean> responseBean = new ResponseBean<>();
		doubtDao.updateDoubt(doubtBean);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubt Updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deletedoubt/{did}")
	public ResponseBean<DoubtBean> deleteDoubt(@PathVariable("did")int did)
	{
		ResponseBean<DoubtBean> responseBean = new ResponseBean<>();
		DoubtBean doubtBean = doubtDao.deleteDoubt(did);
		responseBean.setData(doubtBean);
		responseBean.setMsg("Doubt Deleted.");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/alldoubts")
	public ResponseBean<List<DoubtBean>> getAllDoubts()
	{
		ResponseBean<List<DoubtBean>> responseBean = new ResponseBean<>();
		List<DoubtBean> doubtsBeans = doubtDao.getAll();
		responseBean.setData(doubtsBeans);
		responseBean.setMsg("All Doubts...");
		responseBean.setStatus(200);
		return responseBean;
	}

}
