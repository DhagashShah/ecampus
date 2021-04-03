package com.bean;

import org.springframework.stereotype.Component;

@Component
public class StudentCourseBean 
{
	int userid;
	int[] courseid;
	String firstname;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int[] getCourseid() {
		return courseid;
	}
	public void setCourseid(int[] courseid) {
		this.courseid = courseid;
	}
	
	
	
	
	
}
