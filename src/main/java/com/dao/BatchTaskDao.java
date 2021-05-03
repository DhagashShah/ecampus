package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BatchTaskBean;
import com.bean.StudentTaskBean;
import com.bean.UserBean;

@Repository
public class BatchTaskDao {

	@Autowired
	JdbcTemplate stmt;

	public void addBatchTask(BatchTaskBean batchTaskBean) {
		stmt.update("insert into batchtask(taskid,batchid,adate,sdate,marks,comments) values(?,?,?,?,?,?)",
				batchTaskBean.getTaskid(), batchTaskBean.getBatchid(), batchTaskBean.getAdate(),
				batchTaskBean.getSdate(), batchTaskBean.getMarks(), batchTaskBean.getComments());
	}

	public List<BatchTaskBean> getBatchTaskByFaculty(int userid) {
		List<BatchTaskBean> batchTaskBeans = null;
		batchTaskBeans = stmt.query(
				"select task.taskname,task.path,batchtask.*,batch.name from task,batchtask,batch where task.taskid=batchtask.taskid \r\n"
						+ "and batch.batchid=batchtask.batchid and task.userid=?",
				BeanPropertyRowMapper.newInstance(BatchTaskBean.class), new Object[] { userid });
		return batchTaskBeans;
	}

	public BatchTaskBean getBatchTaskById(int btid) {
		BatchTaskBean batchTaskBean = null;
		batchTaskBean = stmt.queryForObject("select * from batchtask where btid=?",
				BeanPropertyRowMapper.newInstance(BatchTaskBean.class), new Object[] { btid });
		return batchTaskBean;
	}

	public void updateBatchTask(BatchTaskBean batchTaskBean) {
		stmt.update("update batchtask set taskid=?,batchid=?,adate=?,sdate=?,marks=?,comments=? where btid=?",
				batchTaskBean.getTaskid(), batchTaskBean.getBatchid(), batchTaskBean.getAdate(),
				batchTaskBean.getSdate(), batchTaskBean.getMarks(), batchTaskBean.getComments(),
				batchTaskBean.getBtid());
	}

	public BatchTaskBean deleteBatchTask(int btid) {
		BatchTaskBean batchTaskBean = null;
		try {
			batchTaskBean = getBatchTaskById(btid);
			if (batchTaskBean != null) {
				stmt.update("delete from batchtask where btid=?", btid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return batchTaskBean;
	}

	public List<StudentTaskBean> getSubmittedStudents(int btid) {
		List<StudentTaskBean> submitted = null;
		submitted = stmt.query(
				"select users.firstname,users.email,users.contact,batchtask.btid,studenttask.sdate,studenttask.path,studenttask.comments from users,batchtask,studenttask \r\n"
						+ "where users.userid=studenttask.userid\r\n"
						+ "and batchtask.btid=studenttask.btid and batchtask.btid=? ",
				BeanPropertyRowMapper.newInstance(StudentTaskBean.class), new Object[] { btid });
		return submitted;
	}

	public List<UserBean> getNotSubmittedTask(int btid, int btid1) {
		List<UserBean> stuBeans = null;
		stuBeans = stmt.query(
				"select users.* from users where userid in  (  select userid from studentbatch where batchid = (select batchid from batchtask where btid  = ? ) and userid not in (select userid from studenttask where btid = ?))",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { btid, btid1 });
		return stuBeans;
	}

}
