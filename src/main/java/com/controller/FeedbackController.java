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

import com.bean.FeedbackBean;
import com.bean.ResponseBean;
import com.dao.FeedbackDao;

@CrossOrigin
@RestController
public class FeedbackController {

	@Autowired
	FeedbackDao feedbackDao;
	
	@PostMapping("/addfeedback")
	public ResponseBean<FeedbackBean> addFeedback(@RequestBody FeedbackBean feedbackBean)
	{
		ResponseBean<FeedbackBean> responseBean = new ResponseBean<>();
		feedbackDao.insertFeedback(feedbackBean);
		responseBean.setData(feedbackBean);
		responseBean.setMsg("Feedback added....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listfeedback/{userid}")
	public ResponseBean<List<FeedbackBean>> listFeedback(@PathVariable("userid")int userid)
	{
		ResponseBean<List<FeedbackBean>> responseBean = new ResponseBean<>();
		List<FeedbackBean> feedback = feedbackDao.getFeedback(userid);
		responseBean.setData(feedback);
		responseBean.setMsg("Feedback....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getfeedbackbyid/{feedbackid}")
	public ResponseBean<FeedbackBean> getFeedbackById(@PathVariable("feedbackid")int feedbackid)
	{
		ResponseBean<FeedbackBean> responseBean = new ResponseBean<>();
		FeedbackBean feedbackBean = feedbackDao.getFeedbackById(feedbackid);
		if(feedbackBean != null)
		{
			responseBean.setData(feedbackBean);
			System.out.println("Feedback id : "+feedbackBean.getFeedbackid());
			responseBean.setMsg("Feedback fetched....");
			responseBean.setStatus(200);
		}else {
			responseBean.setMsg("Invalid....");
		}
		return responseBean;
	}
	
	@DeleteMapping("/deletefeedback/{feedbackid}")
	public ResponseBean<FeedbackBean> deleteFeedback(@PathVariable("feedbackid")int feedbackid)
	{
		ResponseBean<FeedbackBean> responseBean = new ResponseBean<>();
		FeedbackBean feedbackBean = feedbackDao.deleteFeedback(feedbackid);
		if(feedbackBean!=null)
		{
			responseBean.setData(feedbackBean);
			responseBean.setMsg("Feedback deleted...");
			responseBean.setStatus(200);
		}
		else 
		{
			responseBean.setMsg("Feedback not found");
		}
		
		return responseBean;
	}
	
	@PutMapping("/updatefeedback")
	public ResponseBean<FeedbackBean> updateFeedback(@RequestBody FeedbackBean feedbackBean)
	{
		ResponseBean<FeedbackBean> responseBean = new ResponseBean<>();
		feedbackDao.updateFeedback(feedbackBean);
		responseBean.setData(feedbackBean);
		responseBean.setMsg("Feedbackbean updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listfeedbackadmin/{batchid}")
	public ResponseBean<List<FeedbackBean>> listFeedbackAdmin(@PathVariable("batchid")int batchid)
	{
		ResponseBean<List<FeedbackBean>> responseBean = new ResponseBean<>();
		List<FeedbackBean> feedList = feedbackDao.listFeedbackByBatch(batchid);
		responseBean.setData(feedList);
		responseBean.setMsg("Feedback batchwise....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	
	
	
}
