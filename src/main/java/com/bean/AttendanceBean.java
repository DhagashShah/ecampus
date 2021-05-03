package com.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AttendanceBean {
	
	int batchid;
	int[] userid;
	Date adate;
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public int[] getUserid() {
		return userid;
	}
	public void setUserid(int[] userid) {
		this.userid = userid;
	}
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	
	

}
