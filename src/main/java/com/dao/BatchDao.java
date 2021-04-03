package com.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BatchBean;
import com.bean.StudentBatchBean;
import com.bean.StudentCourseBean;
import com.bean.UserBean;

@Repository
public class BatchDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertBatch(BatchBean batchBean) {
		stmt.update(
				"insert into batch(userid,courseid,name,duration,descr,time,startdate,enddate,isfinish) values(?,?,?,?,?,?,?,?,?)",
				batchBean.getUserid(), batchBean.getCourseid(), batchBean.getName(), batchBean.getDuration(),
				batchBean.getDescr(), batchBean.getTime(), batchBean.getStartdate(), batchBean.getEnddate(),
				batchBean.isIsfinish());
	}

	public BatchBean getBatchById(int batchid) {
		BatchBean batchBean = null;
		batchBean = stmt.queryForObject(
				"select batch.*,course.coursename,course.courseid,users.firstname from batch inner join course on batch.courseid=course.courseid inner join users on batch.userid=users.userid where batch.batchid=?",
				BeanPropertyRowMapper.newInstance(BatchBean.class), new Object[] { batchid });
		return batchBean;
	}

	public List<UserBean> getStudentByCourse(int courseid) {
		List<UserBean> studentCourseBean = null;
		studentCourseBean = stmt.query(
				"select u.* from studentcourse sc inner join users u on  sc.userid=u.userid and sc.courseid=?",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { courseid });
		return studentCourseBean;
	}

	public List<UserBean> getStudentByBatch(int batchid) {
		List<UserBean> students = null;
		students = stmt.query(
				"select users.* from users inner join studentbatch on users.userid=studentbatch.userid and  studentbatch.batchid=?",
				BeanPropertyRowMapper.newInstance(UserBean.class),new Object[] {batchid});
		return students;
	}

	public BatchBean deleteBatch(int batchid) {
		BatchBean batchBean = null;
		try {
			batchBean = getBatchById(batchid);
			if (batchBean != null) {
				stmt.update("delete from batch where batchid=?", batchid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return batchBean;
	}

	public List<BatchBean> getBatches() {
		List<BatchBean> batches = null;
		batches = stmt.query(
				"select batch.batchid,batch.name,batch.time,batch.duration,course.coursename,course.courseid,users.firstname from batch inner join course on batch.courseid=course.courseid inner join users on batch.userid=users.userid",
				BeanPropertyRowMapper.newInstance(BatchBean.class));
		return batches;
	}

	public void updateBatch(BatchBean batchBean) {
		stmt.update(
				"update batch set userid=?,courseid=?,name=?,duration=?,descr=?,time=?,startdate=?,enddate=?,isfinish=? where batchid=?",
				batchBean.getUserid(), batchBean.getCourseid(), batchBean.getName(), batchBean.getDuration(),
				batchBean.getDescr(), batchBean.getStartdate(), batchBean.getEnddate(), batchBean.isIsfinish());
	}

	public void addStudentToBatch(StudentBatchBean studentBatchBean) {
		for (int u : studentBatchBean.getUserid()) {
			stmt.update("insert into studentbatch(userid,batchid) values(?,?)", u, studentBatchBean.getBatchid());
		}
	}

}
