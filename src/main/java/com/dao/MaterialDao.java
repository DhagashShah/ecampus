package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.MaterialBean;

@Repository
public class MaterialDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertNotes(MaterialBean materialBean) {
		stmt.update("insert into material(userid,courseid,path,active) values(?,?,?,?)", materialBean.getUserid(),
				materialBean.getCourseid(), materialBean.getPath(), materialBean.isActive());
	}

	public MaterialBean getMaterialById(int mid) {
		MaterialBean materialBean = null;
		materialBean = stmt.queryForObject("select * from material where mid=?",
				BeanPropertyRowMapper.newInstance(MaterialBean.class));
		return materialBean;
	}

	public MaterialBean deleteMaterial(int mid) {
		
		MaterialBean materialBean = null;
		try 
		{
			materialBean = getMaterialById(mid);
			if (materialBean!=null) 
			{
				stmt.update("delete from material where mid=?",mid);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return materialBean;
	}

}
