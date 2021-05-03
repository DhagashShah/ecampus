package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.StudentTaskBean;

@Repository
public class StudentTaskDao {

	@Autowired
	JdbcTemplate stmt;

	public List<StudentTaskBean> getBatchTask(int batchid, int userid) {
		List<StudentTaskBean> studentTaskBeans = null;
		studentTaskBeans = stmt.query(
				"select task.taskname,task.path,batchtask.adate,batchtask.sdate,batchtask.marks,batchtask.comments,studenttask.stid from task,batchtask,studenttask \r\n"
				+ "where task.taskid=batchtask.taskid and studenttask.btid=batchtask.btid and batchtask.batchid=? and studenttask.userid=?",
				BeanPropertyRowMapper.newInstance(StudentTaskBean.class), new Object[] { batchid, userid });
		return studentTaskBeans;
	}

	public void addStudentTask(StudentTaskBean studentTaskBean) {
		stmt.update("insert into studenttask(userid,btid,sdate,path,comments) values(?,?,?,?,?)",
				studentTaskBean.getUserid(), studentTaskBean.getBtid(), studentTaskBean.getSdate(),
				studentTaskBean.getPath(), studentTaskBean.getComments());
	}

	public List<StudentTaskBean> getNotSubmittedTask(int batchid, int userid) {
		List<StudentTaskBean> studentTaskBeans = null;
		studentTaskBeans = stmt.query(
				"select batchtask.*,task.taskname,task.path from batchtask,task where batchtask.taskid=task.taskid \r\n"
						+ "and batchid=? and btid not  in (select btid from studenttask where userid=?)",
				BeanPropertyRowMapper.newInstance(StudentTaskBean.class), new Object[] { batchid, userid });
		return studentTaskBeans;
	}

	public List<StudentTaskBean> getStudentTaskById(int stid) {
		List<StudentTaskBean> studentTaskBean = null;
		studentTaskBean = stmt.query("select * from studenttask where stid=?",
				BeanPropertyRowMapper.newInstance(StudentTaskBean.class), new Object[] { stid });
		return studentTaskBean;
	}
}
