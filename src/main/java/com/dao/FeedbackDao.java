package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.FeedbackBean;

@Repository
public class FeedbackDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertFeedback(FeedbackBean feedbackBean) {
		stmt.update(
				"insert into feedback(userid,bid,explanation,material,punctuality,teaching,communication,comments) values(?,?,?,?,?,?,?,?)",
				feedbackBean.getUserid(), feedbackBean.getBid(), feedbackBean.getExplanation(),
				feedbackBean.getMaterial(), feedbackBean.getPunctuality(), feedbackBean.getTeaching(),
				feedbackBean.getCommunication(), feedbackBean.getComments());
	}

	public List<FeedbackBean> getFeedback(int userid) {
		List<FeedbackBean> feedbacks = null;
		feedbacks = stmt.query(
				"select batch.name,batch.batchid,feedback.* from batch inner join feedback on  \r\n"
						+ "batch.batchid=feedback.bid where feedback.userid=?",
				BeanPropertyRowMapper.newInstance(FeedbackBean.class), new Object[] { userid });
		return feedbacks;
	}

	public FeedbackBean getFeedbackById(int feedbackid) {
		FeedbackBean feedbackBean = null;
		feedbackBean = stmt.queryForObject("select * from feedback where feedbackid=?",
				BeanPropertyRowMapper.newInstance(FeedbackBean.class), new Object[] { feedbackid });
		return feedbackBean;
	}

	public FeedbackBean deleteFeedback(int feedbackid) {
		FeedbackBean feedbackBean = null;
		try {
			feedbackBean = getFeedbackById(feedbackid);
			if (feedbackBean != null) {
				stmt.update("delete from feedback where feedbackid=?", feedbackid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackBean;
	}

	public void updateFeedback(FeedbackBean feedbackBean) {
		stmt.update(
				"update feedback set userid=?,bid=?,explanation=?,material=?,punctuality=?,teaching=?,communication=?,comments=? where feedbackid=?",
				feedbackBean.getUserid(), feedbackBean.getBid(), feedbackBean.getExplanation(),
				feedbackBean.getMaterial(), feedbackBean.getPunctuality(), feedbackBean.getTeaching(),
				feedbackBean.getCommunication(), feedbackBean.getComments(), feedbackBean.getFeedbackid());
	}

	public List<FeedbackBean> listFeedbackByBatch(int batchid) 
	{
		List<FeedbackBean> feedList  = null;
		feedList=stmt.query("select batch.batchid,feedback.*,users.firstname from batch,feedback,users where \r\n"
				+ "batch.batchid=feedback.bid and feedback.userid=users.userid and batch.batchid=?",BeanPropertyRowMapper.newInstance(FeedbackBean.class), new Object[] {batchid});
		return feedList;
	}

}
