package com.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AttendanceBean;
import com.bean.BatchBean;
import com.bean.CountBean;
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
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { batchid });
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
				batchBean.getDescr(), batchBean.getTime(), batchBean.getStartdate(), batchBean.getEnddate(),
				batchBean.isIsfinish(), batchBean.getBatchid());
	}

	public void addStudentToBatch(StudentBatchBean studentBatchBean) {
		for (int u : studentBatchBean.getUserid()) {
			stmt.update("insert into studentbatch(userid,batchid) values(?,?)", u, studentBatchBean.getBatchid());
		}
	}

	public void addStudentAttendance(AttendanceBean attendanceBean) {
		for (int u : attendanceBean.getUserid()) {
			stmt.update("insert into attendance(batchid,userid,adate) values(?,?,?)", attendanceBean.getBatchid(), u,
					attendanceBean.getAdate());
		}
	}

	public List<UserBean> getStudentNotInBatch(int courseid, int batchid) {
		List<UserBean> students = null;
		students = stmt.query(
				"select users.*,studentcourse.* from users inner join studentcourse on users.userid = studentcourse.userid where users.roleid=3 and studentcourse.courseid=? and users.userid not in ( select userid from studentbatch where batchid=? )",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { courseid, batchid });
		return students;
	}

	public List<UserBean> getStudentByDate(Date adate, int batchid) {
		List<UserBean> students = null;
		students = stmt.query(
				"select users.*,attendance.adate from users,attendance where users.userid=attendance.userid and attendance.adate=? and batchid=?",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { adate, batchid });
		return students;
	}

	public List<UserBean> getAbsentStudents(int batchid, Date adate, Date adate2) {
		List<UserBean> students = null;
		students = stmt.query(
				"select distinct users.firstname,users.*  from users,studentbatch,attendance \r\n"
						+ "where users.userid=studentbatch.userid and studentbatch.batchid=? \r\n"
						+ "and attendance.adate = ? and users.userid NOT IN\r\n"
						+ "(select distinct userid from attendance where  adate =?)",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { batchid, adate, adate2 });
		return students;
	}

	public UserBean getStudentByBatch1(int userid) {
		UserBean userBean = null;
		userBean = stmt.queryForObject("select * from studentbatch where userid=?",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { userid });
		return userBean;
	}

	public UserBean deleteBatchStudent(int userid) {
		UserBean userBean = null;
		try {
			userBean = getStudentByBatch1(userid);
			if (userBean != null) {
				stmt.update("delete from studentbatch where userid=?", userid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}

	public List<BatchBean> getBatchByFaculty(int userid) {
		List<BatchBean> batches = null;
		batches = stmt.query(
				"select batch.*,course.* from batch inner join course on batch.courseid=course.courseid where userid=?",
				BeanPropertyRowMapper.newInstance(BatchBean.class), new Object[] { userid });
		return batches;
	}

	public List<BatchBean> getBatchByStudent(int userid) {
		List<BatchBean> batches = null;
		batches = stmt.query(
				"select batch.*,studentbatch.*,users.firstname from batch,studentbatch,users where batch.batchid=studentbatch.batchid \r\n"
						+ "and studentbatch.userid=? and users.userid=batch.userid ",
				BeanPropertyRowMapper.newInstance(BatchBean.class), new Object[] { userid });
		return batches;
	}

	public CountBean getTotalBatch() {
		CountBean c = stmt.queryForObject("select count(*) from batch",
				BeanPropertyRowMapper.newInstance(CountBean.class));
		return c;
	}

	public List<AttendanceBean> getDateByBatch(int batchid) {
		List<AttendanceBean> dates = null;
		dates = stmt.query("select distinct(adate),batchid from attendance where  batchid=?  order by adate desc",
				BeanPropertyRowMapper.newInstance(AttendanceBean.class), new Object[] { batchid });
		return dates;
	}

	public CountBean getTotalSession(int batchid) {
		CountBean countBean = null;
		countBean = stmt.queryForObject("select count(distinct adate) from attendance where batchid=?",
				BeanPropertyRowMapper.newInstance(CountBean.class), new Object[] { batchid });
		return countBean;
	}

}
