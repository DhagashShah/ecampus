package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.TaskBean;

@Repository
public class TaskDao {
	@Autowired
	JdbcTemplate stmt;

	public void addTask(TaskBean taskBean) {
		stmt.update("insert into task(taskname,courseid,userid,path) values(?,?,?,?)", taskBean.getTaskname(),
				taskBean.getCourseid(), taskBean.getUserid(), taskBean.getPath());
	}

	public List<TaskBean> getTaskByUser(int userid) {
		List<TaskBean> taskBeans = null;
		taskBeans = stmt.query(
				"select task.*,course.coursename from task,course where task.courseid=course.courseid and userid=? \r\n"
						+ "and taskid not in (select taskid from batchtask)",
				BeanPropertyRowMapper.newInstance(TaskBean.class), new Object[] { userid });
		return taskBeans;
	}

	public List<TaskBean> getAssignedTaskByUser(int userid) {
		List<TaskBean> taskBeans = null;
		taskBeans = stmt.query(
				"select task.*,course.coursename from task,course where task.courseid=course.courseid and userid=? \r\n"
						+ "and taskid in (select taskid from batchtask)",
				BeanPropertyRowMapper.newInstance(TaskBean.class), new Object[] { userid });
		return taskBeans;
	}

	public List<TaskBean> getAllTaskByUser(int userid) {
		List<TaskBean> taskBeans = null;
		taskBeans = stmt.query(
				"select task.*,course.coursename from task,course where task.courseid=course.courseid and userid=?",
				BeanPropertyRowMapper.newInstance(TaskBean.class), new Object[] { userid });
		return taskBeans;
	}

	public TaskBean getTaskById(int taskid) {
		TaskBean taskBean = null;
		taskBean = stmt.queryForObject("select * from task where taskid=?",
				BeanPropertyRowMapper.newInstance(TaskBean.class), new Object[] { taskid });
		return taskBean;
	}

	public void updateTask(TaskBean taskBean) {
		stmt.update("update task set taskname=?,courseid=?,userid=?,path=? where taskid=?", taskBean.getTaskname(),
				taskBean.getCourseid(), taskBean.getUserid(), taskBean.getPath(), taskBean.getTaskid());
	}

	public TaskBean deleteTask(int taskid) {
		TaskBean taskBean = null;
		try {
			taskBean = getTaskById(taskid);
			if (taskBean != null) {
				stmt.update("delete from task where taskid=?", taskid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskBean;
	}

}
