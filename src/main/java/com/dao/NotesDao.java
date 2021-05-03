package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.NotesBean;

@Repository
public class NotesDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertNotes(NotesBean notesBean) {
		stmt.update("insert into notes(userid,courseid,path) values(?,?,?)", notesBean.getUserid(),
				notesBean.getCourseid(), notesBean.getPath());
	}

	public List<NotesBean> getNotesByUser(int userid) {
		List<NotesBean> notesBeans = null;
		notesBeans = stmt.query(
				"select notes.*,course.coursename from notes,course where course.courseid=notes.courseid and  userid=?",
				BeanPropertyRowMapper.newInstance(NotesBean.class), new Object[] { userid });
		return notesBeans;
	}

	public NotesBean getNotesById(int notesid) {
		NotesBean notesBean = null;
		notesBean = stmt.queryForObject("select * from notes where notesid=?",
				BeanPropertyRowMapper.newInstance(NotesBean.class), new Object[] { notesid });
		return notesBean;
	}

	public NotesBean deleteNotes(int notesid) {
		NotesBean notesBean = null;
		try {
			notesBean = getNotesById(notesid);
			if (notesBean != null) {
				stmt.update("delete from notes where notesid=?", notesid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notesBean;
	}

	public void updateNotes(NotesBean notesBean) {
		stmt.update("update notes set courseid=?,path=? where notesid=?", notesBean.getCourseid(), notesBean.getPath(),
				notesBean.getNotesid());
	}

	public List<NotesBean> getNotesByCourse(int courseid) {
		List<NotesBean> notesBeans = null;
		notesBeans = stmt.query(
				"select users.firstname,notes.* from notes,users where users.userid=notes.userid and courseid=?",
				BeanPropertyRowMapper.newInstance(NotesBean.class), new Object[] { courseid });
		return notesBeans;
	}
}
