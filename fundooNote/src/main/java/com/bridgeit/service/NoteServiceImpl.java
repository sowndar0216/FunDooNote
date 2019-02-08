package com.bridgeit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.INoteDao;
import com.bridgeit.model.Note;
import com.bridgeit.model.User;
import com.bridgeit.utility.UserToken;

@Service
@Transactional
public class NoteServiceImpl implements INoteService {

	@Autowired
	private INoteDao noteDao;

	@Autowired
	private IUserService userService;

	@Override
	public boolean addNote(Note note, String token) {
		// noteDao.addNote(note
		System.out.println(note);
		try {
			int id = UserToken.tokenVerify(token);
			User user = userService.getUser(id);
			System.out.println(id);
			note.setUser(user);

			noteDao.addNote(note);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void deleteNote(Note note, String token) {

		try {
			int id = UserToken.tokenVerify(token);
			User user = userService.getUser(id);
			System.out.println(id);
			note.setUser(user);
			System.out.println();
			noteDao.deleteNote(note);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean updateArchiveNote(Note note) {
		// TODO Auto-generated method stub

		if (note.isArchive() == false) {
			note.setArchive(true);
		} else {
			note.setArchive(false);
		}

		System.out.println("after delete to 1  " + note);
		noteDao.updateNote(note);

		return true;
	}

	@Override
	public List<Note> getNotes(String token) {

		try {
			int id = UserToken.tokenVerify(token);
			// User user=userService.getUser(id);
			System.out.println(id);

			List<Note> listAll = noteDao.getNotes(id);

			return listAll;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void archiveNote(Note note, String token) {
		try {
			int id = UserToken.tokenVerify(token);
			User user = userService.getUser(id);
			System.out.println(id);
			note.setUser(user);
			note.setArchive(true);
			noteDao.addNote(note);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateTrashNote(Note note) {
		// TODO Auto-generated method stub
		if (note.isTrash() == false) {
			note.setTrash(true);
		} else {
			note.setTrash(false);
		}

		return noteDao.updateNote(note);

	}

	@Override
	public void updateRestoreNote(Note note) {


			if (note.isTrash() == false) {
				note.setTrash(true);
			} else {
				note.setTrash(false);
			}

			System.out.println("after delete to 1  " + note);
			noteDao.updateNote(note);

		
	}

	@Override
	public boolean updateColorsNote(Note note) {
		// TODO Auto-generated method stub
	

			
				noteDao.updateNote(note);
				System.out.println("after" + note);
				return true;
			


	}

	@Override
	public void deleteNoteForever(Note note) {
		// TODO Auto-generated method stub

		noteDao.deleteNoteForever(note);

	}

	@Override
	public void updateEditNote(Note note) {
		// TODO Auto-generated method stub
		System.out.println("going  to update ");
		noteDao.updateNote(note);
		System.out.println("updated suceesfully");
	}

	@Override
	public Note getNoteById(int id) {

		return noteDao.getNoteById(id);
	}

	@Override
	public boolean verifyUser(String token, Note note) {

		try {
			int id = UserToken.tokenVerify(token);
			if (note.getUser().getId() == id) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updatePinNote(Note note) {


		if (note.isPinned() == false) {
			note.setPinned(true);
		} else {
			note.setPinned(false);
		}

		noteDao.updateNote(note);
		
		
		return false;
	}

}
