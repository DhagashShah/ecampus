package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.dao.RoleDao;

@CrossOrigin
@RestController
public class RoleController 
{
	@Autowired
	RoleDao roleDao;
	
	@PostMapping("/addrole")
	public ResponseBean<RoleBean> insertRole(@RequestBody RoleBean roleBean)
	{
		ResponseBean<RoleBean> responseBean = new ResponseBean<>();
		roleDao.insertRole(roleBean);
		responseBean.setData(roleBean);
		responseBean.setMsg("Role inserted..");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deleterole/{roleid}")
	public ResponseBean<RoleBean> deleteRole(@PathVariable("roleid") int roleid)
	{
		ResponseBean<RoleBean> responseBean = new ResponseBean<>();
		RoleBean roleBean = roleDao.deleteRole(roleid);
		if(roleBean!=null)
		{
			responseBean.setData(roleBean);
			responseBean.setMsg("Role deleted...");
		}
		else 
		{
			responseBean.setMsg("Role not found..");
		}
		return responseBean;
	}
	
	@GetMapping("/roles")
	public ResponseBean<List<RoleBean>> getAllRoles()
	{
		ResponseBean<List<RoleBean>> responseBean = new ResponseBean<>();
		List<RoleBean> roles=roleDao.getAllRoles();
		responseBean.setData(roles);
		responseBean.setMsg("All Roles..");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updaterole")
	public ResponseBean<RoleBean> updateRole(@RequestBody RoleBean roleBean)
	{
		ResponseBean<RoleBean> responseBean = new ResponseBean<>();
		roleDao.updateRole(roleBean);
		responseBean.setData(roleBean);
		responseBean.setMsg("Role Updated..");
		responseBean.setStatus(200);
		return responseBean;
	}
}
