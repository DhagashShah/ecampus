package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CountBean;
import com.bean.CourseBean;
import com.bean.StudentCourseBean;
import com.bean.UserBean;

@Repository
public class CourseDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertCourse(CourseBean courseBean) {
		stmt.update("insert into course(coursename,duration) values(?,?)", courseBean.getCoursename(),
				courseBean.getDuration());
	}

	public List<CourseBean> allCourse() {
		List<CourseBean> courseBeans = stmt.query("select * from course order by courseid asc",
				BeanPropertyRowMapper.newInstance(CourseBean.class));
		return courseBeans;
	}

	public void updateCourse(CourseBean courseBean) {
		stmt.update("update course set coursename=?,duration=? where courseid=?", courseBean.getCoursename(),
				courseBean.getDuration(), courseBean.getCourseid());
	}

	public CourseBean deleteCourse(int courseid) {
		CourseBean courseBean = getCourseById(courseid);
		if (courseBean != null) {
			stmt.update("delete from course where courseid=?", courseid);
		}
		return courseBean;
	}

	public CourseBean getCourseById(int courseid) {
		CourseBean courseBean = null;
		try {
			courseBean = stmt.queryForObject("select * from course where courseid=?",
					BeanPropertyRowMapper.newInstance(CourseBean.class), new Object[] { courseid });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBean;
	}

	public void insertStudentCourse(StudentCourseBean studentCourseBean) {
		for (int c : studentCourseBean.getUserid()) {
			stmt.update("insert into studentcourse(userid,courseid) values(?,?)", c, studentCourseBean.getCourseid());
		}

	}

	public List<UserBean> getStudent(int courseid) {
		List<UserBean> users = null;
		users = stmt.query(
				"select * from users where roleid=3 and active=true and userid not in (select userid from studentcourse where courseid=?)",
				BeanPropertyRowMapper.newInstance(UserBean.class), new Object[] { courseid });
		return users;
	}

	public CountBean getTotalCourse() {
		CountBean c = stmt.queryForObject("select count(*) from course",
				BeanPropertyRowMapper.newInstance(CountBean.class));
		return c;
	}

	public List<CourseBean> getStudentByCourse(int userid) {
		List<CourseBean> courses = null;
		courses = stmt.query(
				"select users.userid,course.*,studentcourse.* from users,course,studentcourse where users.userid=studentcourse.userid and course.courseid=studentcourse.courseid and studentcourse.userid=?",
				BeanPropertyRowMapper.newInstance(CourseBean.class), new Object[] { userid });
		return courses;
	}
}
