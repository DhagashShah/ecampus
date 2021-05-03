package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ComplainBean;

@Repository
public class ComplainDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertComplain(ComplainBean complainBean) {
		stmt.update("insert into complain(sid,complain,isresolve,cdate) values(?,?,?,?)", complainBean.getSid(),
				complainBean.getComplain(), complainBean.isIsresolve(),complainBean.getCdate());
	}

	public List<ComplainBean> getComplainByStudent(int userid) {
		List<ComplainBean> complainBeans = null;
		try {
			complainBeans = stmt.query("select * from complain where sid=?",
					BeanPropertyRowMapper.newInstance(ComplainBean.class), new Object[] { userid });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return complainBeans;
	}

	public List<ComplainBean> getAllComplain() 
	{
		List<ComplainBean> comList = null;
		comList = stmt.query("select complain.*,users.firstname from complain inner join users on \r\n"
				+ "complain.sid=users.userid where isresolve=false",BeanPropertyRowMapper.newInstance(ComplainBean.class));
		return comList;
	}
	
	public List<ComplainBean> getResolvedComplain(int userid) 
	{
		List<ComplainBean> complainBeans = null;
		complainBeans = stmt.query("select complain.* from complain where isresolve=true and  sid=?",BeanPropertyRowMapper.newInstance(ComplainBean.class),new Object[] {userid});
		return complainBeans;
	}

	public void updateComplain(ComplainBean complainBean) 
	{
		stmt.update("update complain set isresolve=?,rdate=?,comment=? where cid=?",complainBean.isIsresolve(),complainBean.getRdate(),complainBean.getComment(),complainBean.getCid());
	}

	public ComplainBean getComplainById(int cid) 
	{
		ComplainBean complainBean = null;
		complainBean= stmt.queryForObject("select * from complain where cid=?",BeanPropertyRowMapper.newInstance(ComplainBean.class),new Object[] {cid});
		return complainBean;
	}

	

}
