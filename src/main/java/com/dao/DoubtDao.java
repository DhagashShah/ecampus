package com.dao;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.DoubtBean;

@Repository
public class DoubtDao {

	@Autowired
	JdbcTemplate stmt;

	public void addDoubt(DoubtBean doubtBean) {
		stmt.update("insert into doubt(sid,cid,detail,cdate,accepted) values(?,?,?,?,?)", doubtBean.getSid(),
				doubtBean.getCid(), doubtBean.getDetail(), doubtBean.getCdate(), doubtBean.isAccepted());
	}

	public List<DoubtBean> getDoubts(int sid) {
		List<DoubtBean> doubts = null;
		doubts = stmt.query(
				"select doubt.*,course.coursename,users.firstname from doubt,course,users\r\n"
				+ "where doubt.cid=course.courseid and doubt.tid=users.userid and sid=? and doubt.accepted=true",
				BeanPropertyRowMapper.newInstance(DoubtBean.class), new Object[] { sid });
		return doubts;
	}
	
	public List<DoubtBean> getNotAcceptedDoubtById(int sid) {

		List<DoubtBean> doubts = null;
		doubts = stmt.query(
				"select doubt.did,doubt.detail,doubt.cdate,doubt.accepted,course.coursename from doubt,course where doubt.cid=course.courseid and sid=? and doubt.accepted=false",
				BeanPropertyRowMapper.newInstance(DoubtBean.class), new Object[] { sid });
		return doubts;
	}


	public List<DoubtBean> getDoubtByCourse(int cid, int sid) {
		List<DoubtBean> doubts = null;
		doubts = stmt.query(
				"select users.firstname,course.coursename,doubt.did,doubt.detail,doubt.cdate,doubt.accepted,doubt.sid,doubt.cid \r\n"
						+ "from users,course,doubt where \r\n"
						+ "users.userid=doubt.sid and course.courseid=doubt.cid and doubt.cid=? and doubt.accepted=false\r\n"
						+ "and not doubt.sid=?",
				BeanPropertyRowMapper.newInstance(DoubtBean.class), new Object[] { cid, sid });
		return doubts;
	}

	public DoubtBean getDoubtById(int did) {
		DoubtBean doubtBean = null;
		doubtBean = stmt.queryForObject("select did,sid,cid,detail,cdate,accepted from doubt where did=?",
				BeanPropertyRowMapper.newInstance(DoubtBean.class), new Object[] { did });
		return doubtBean;
	}


	public void updateDoubt(DoubtBean doubtBean) {
		stmt.update("update doubt set tid=?,session=?,sdate=?,stime=?,accepted=? where did=?", doubtBean.getTid(),
				doubtBean.getSession(), doubtBean.getSdate(), doubtBean.getStime(), doubtBean.isAccepted(),
				doubtBean.getDid());
	}

	public void updateNotAcceptedDoubt(DoubtBean doubtBean) 
	{
		stmt.update("update doubt set sid=?,cid=?,detail=?,cdate=?,accepted=? where did=?", doubtBean.getSid(),
				doubtBean.getCid(), doubtBean.getDetail(), doubtBean.getCdate(), doubtBean.isAccepted(),doubtBean.getDid());
	}

	public DoubtBean deleteDoubt(int did) 
	{
		DoubtBean doubtBean = null;
		try 
		{
			doubtBean = getDoubtById(did);
			if(doubtBean!=null)
			{
				stmt.update("delete from doubt where did=?",did);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return doubtBean;
	}

	public List<DoubtBean> getAll() 
	{
		List<DoubtBean> doubtBeans = null;
		doubtBeans = stmt.query("select su.firstname as firstname,st.firstname as tname,d.* from users as su,users as st,doubt as d \r\n"
				+ "where su.userid=d.sid and st.userid=d.tid ",BeanPropertyRowMapper.newInstance(DoubtBean.class));
		return doubtBeans;
	}

	public List<DoubtBean> getAcceptedDoubts(int cid) 
	{
		List<DoubtBean> doubtBeans = null;
		doubtBeans = stmt.query("select  q.firstname,a.firstname as tname,d.detail,d.session,d.sdate,d.stime,course.coursename from users q,users a,doubt d,course \r\n"
				+ "where d.sid = q.userid and d.tid = a.userid and course.courseid=d.cid and d.cid=?",BeanPropertyRowMapper.newInstance(DoubtBean.class),new Object[] {cid});
		return doubtBeans;
	}

}
