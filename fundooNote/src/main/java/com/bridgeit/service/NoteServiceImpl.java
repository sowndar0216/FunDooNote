package com.bridgeit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.INoteDao;
import com.bridgeit.model.Note;
import com.bridgeit.model.User;

@Service
@Transactional
public class NoteServiceImpl implements INoteService
{

	@Autowired
	private INoteDao noteDao;
	
	@Override
	public boolean addNote(Note note, User user) {
		noteDao.addNote(note,user);
		return false;
	}

	@Override
	public void deleteNote(Note note) {

		noteDao.deleteNote(note);
	}

	@Override
	public void updateNote(Note note) {
		// TODO Auto-generated method stub
		noteDao.updateNote(note);
	}

	

}
