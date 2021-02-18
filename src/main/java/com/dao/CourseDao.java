package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CourseBean;

@Repository
public class CourseDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertCourse(CourseBean courseBean) {
		stmt.update("insert into course(coursename,duration) values(?,?)", courseBean.getCoursename(),
				courseBean.getDuration());
	}

	public List<CourseBean> allCourse() {
		List<CourseBean> courseBeans = stmt.query("select * from course",
				BeanPropertyRowMapper.newInstance(CourseBean.class));
		return courseBeans;
	}

	public void updateCourse(CourseBean courseBean) {
		stmt.update("update course set coursename=?,duration=? where courseid=?", courseBean.getCoursename(),
				courseBean.getDuration(), courseBean.getCourseid());
	}

	public CourseBean deleteCourse(int courseid) {
		CourseBean courseBean = getCourseById(courseid);
		if (courseBean!=null) 
		{
			stmt.update("delete from course where courseid=?",courseid);
		}
		return courseBean;
	}

	private CourseBean getCourseById(int courseid) {
		CourseBean courseBean = null;
		try {
			courseBean = stmt.queryForObject("select * from course where courseid=?",
					BeanPropertyRowMapper.newInstance(CourseBean.class), new Object[] { courseid });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseBean;
	}
}
