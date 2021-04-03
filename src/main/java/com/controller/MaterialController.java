package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.BatchBean;
import com.bean.MaterialBean;
import com.bean.ResponseBean;
import com.dao.MaterialDao;

@RestController
public class MaterialController 
{
	@Autowired
	MaterialDao materialDao;
	
	@PostMapping("/addnotes")
	public ResponseBean<MaterialBean> addMaterial(@RequestBody MaterialBean materialBean)
	{
		ResponseBean<MaterialBean> responseBean= new ResponseBean<>();
		materialDao.insertNotes(materialBean);
		responseBean.setData(materialBean);
		responseBean.setMsg("Material added..");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deletenotes/{mid}")
	public ResponseBean<MaterialBean> deleteMaterial(@PathVariable("mid")int  mid)
	{
		ResponseBean<MaterialBean> responseBean = new ResponseBean<>();
		MaterialBean materialBean = materialDao.deleteMaterial(mid);
		responseBean.setData(null);
		responseBean.setMsg("notes deleted...");
		responseBean.setStatus(200);
		return responseBean;
	}
}
