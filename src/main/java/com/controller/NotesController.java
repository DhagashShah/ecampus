package com.controller;

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
import com.bean.NotesBean;
import com.bean.ResponseBean;
import com.dao.NotesDao;

@CrossOrigin
@RestController
public class NotesController {

	@Autowired
	NotesDao notesDao;
	
	
	
	@PostMapping("/savenotes")
	public ResponseBean<NotesBean> insertNotes(@RequestBody NotesBean notesBean) 
	{
		ResponseBean<NotesBean> responseBean = new ResponseBean<>();
		notesDao.insertNotes(notesBean);
		responseBean.setData(notesBean);
		responseBean.setMsg("Notes Added...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getnotesbyuserid/{userid}")
	public ResponseBean<List<NotesBean>> getNotesByUser(@PathVariable("userid")int userid)
	{
		ResponseBean<List<NotesBean>> responseBean = new ResponseBean<>();
		List<NotesBean> notes = notesDao.getNotesByUser(userid);
		responseBean.setData(notes);
		responseBean.setMsg("Notes by Faculty");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getnotesbyid/{notesid}")
	public ResponseBean<NotesBean> getNotesById(@PathVariable("notesid")int notesid)
	{
		ResponseBean<NotesBean> responseBean = new ResponseBean<>();
		NotesBean notesBean = notesDao.getNotesById(notesid);
		responseBean.setData(notesBean);
		responseBean.setMsg("NotesBean");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("deletenotes/{notesid}")
	public ResponseBean<NotesBean> deleteNotes(@PathVariable("notesid")int notesid)
	{
		ResponseBean<NotesBean> responseBean = new ResponseBean<>();
		NotesBean notesBean = notesDao.deleteNotes(notesid);
		responseBean.setData(notesBean);
		responseBean.setMsg("Note Deleted....");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updatenotes")
	public ResponseBean<NotesBean> updateNotes(@RequestBody NotesBean notesBean)
	{
		ResponseBean<NotesBean> responseBean = new ResponseBean<>();
		notesDao.updateNotes(notesBean);
		responseBean.setData(notesBean);
		responseBean.setMsg("Notes Updated...");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/getNotesByCourse/{courseid}")
	public ResponseBean<List<NotesBean>> getNotesByCourse(@PathVariable("courseid")int courseid)
	{
		ResponseBean<List<NotesBean>> responseBean = new ResponseBean<>();
		List<NotesBean> notesBeans = notesDao.getNotesByCourse(courseid);
		if(notesBeans!=null)
		{
			responseBean.setData(notesBeans);
			responseBean.setMsg("Notes Fetched...");
			responseBean.setStatus(201);
		}
		else
		{
			responseBean.setMsg("Invalid....");
			responseBean.setStatus(200);
		}
		
		return responseBean;
	}
}
