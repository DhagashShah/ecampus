package com.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ComplainBean {
	int cid;
	int sid;
	String complain;
	boolean isresolve;
	Date cdate;
	Date rdate;
	String firstname;
	String comment;
	
	
	
	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public boolean isIsresolve() {
		return isresolve;
	}

	public void setIsresolve(boolean isresolve) {
		this.isresolve = isresolve;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

}
