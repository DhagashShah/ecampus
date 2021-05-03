package com.bean;

import org.springframework.stereotype.Component;

@Component
public class FeedbackBean 
{
	int feedbackid;
	int userid;
	int  batchid;
	int explanation;
	int material;
	int bid;
	int punctuality;
	int teaching;
	int communication;
	String comments;
	String name;
	String firstname;
	
	
	
	public int getTeaching() {
		return teaching;
	}
	public void setTeaching(int teaching) {
		this.teaching = teaching;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public int getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(int feedbackid) {
		this.feedbackid = feedbackid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public int getExplanation() {
		return explanation;
	}
	public void setExplanation(int explanation) {
		this.explanation = explanation;
	}
	public int getMaterial() {
		return material;
	}
	public void setMaterial(int material) {
		this.material = material;
	}
	public int getPunctuality() {
		return punctuality;
	}
	public void setPunctuality(int punctuality) {
		this.punctuality = punctuality;
	}
	
	public int getCommunication() {
		return communication;
	}
	public void setCommunication(int communication) {
		this.communication = communication;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
