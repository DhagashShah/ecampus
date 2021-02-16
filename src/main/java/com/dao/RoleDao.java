package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.RoleBean;

@Repository
public class RoleDao {
	@Autowired
	JdbcTemplate stmt;

	public void insertRole(RoleBean roleBean) {
		stmt.update("insert into role(rolename) values(?)", roleBean.getRolename());
	}

	public RoleBean getRoleById(int roleid) {
		RoleBean roleBean = null;
		try {
			roleBean = stmt.queryForObject("select * from role where roleid=?",
					BeanPropertyRowMapper.newInstance(RoleBean.class), new Object[] { roleid });
		} catch (Exception e) {
			System.out.println("Role not found");
		}
		return roleBean;
	}
	
	public RoleBean deleteRole(int roleid)
	{
		RoleBean roleBean = null;
		try 
		{
			roleBean=getRoleById(roleid);
			if(roleBean != null)
			{
				stmt.update("delete from role where roleid=?",roleid);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return roleBean;
		
		 
	}

	public List<RoleBean> getAllRoles() 
	{
		List<RoleBean> roles=null;
		try 
		{
			roles = stmt.query("select * from role", BeanPropertyRowMapper.newInstance(RoleBean.class));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return roles;
	}

	public void updateRole(RoleBean roleBean) 
	{
		stmt.update("update role set rolename=? where roleid=?",roleBean.getRolename(),roleBean.getRoleid());
	}
}
