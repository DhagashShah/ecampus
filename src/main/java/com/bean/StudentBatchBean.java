package com.bean;

import org.springframework.stereotype.Component;

@Component
public class StudentBatchBean 
{
	int[] userid;
	int batchid;
	public int[] getUserid() {
		return userid;
	}
	public void setUserid(int[] userid) {
		this.userid = userid;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	
	
}
