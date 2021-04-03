package com.bean;

import org.springframework.stereotype.Component;

@Component
public class UserBean 
{
	int userid;
	int roleid;
	String firstname;
	String lastname;
	String email;
	String password;
	char gender;
	long  contact;
	
	String rolename;
	
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	
	
	public enum Role
	{
		ADMIN(1),FACULTY(2),STUDENT(3);
		public int roleid;
		Role(int roleid) 
		{
			this.roleid=roleid;
		}
	}
	
	public boolean isAdmin()
	{
		if(roleid==Role.ADMIN.roleid)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean isFaculty()
	{
		if(roleid==Role.FACULTY.roleid)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean isStudent()
	{
		if(roleid==Role.STUDENT.roleid)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
}
