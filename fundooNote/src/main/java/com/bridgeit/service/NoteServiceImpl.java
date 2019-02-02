package com.bridgeit.service;

import java.util.ArrayList;
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
public class NoteServiceImpl implements INoteService
{

	@Autowired
	private INoteDao noteDao;
	
	@Autowired 
	private IUserService userService;
	
	
	@Override
	public boolean addNote(Note note, String token) {
		//noteDao.addNote(note,);
		
		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);
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
	public void deleteNote(Note note,String token) {

		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);
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
	public void updateNote(Note note,String token) {
		// TODO Auto-generated method stub
		try {
			int id=UserToken.tokenVerify(token);
//			User user=userService.getUser(id);
//			System.out.println(id);
		//	note.setUser(user);

			if(note.isArchive()==false) {
			note.setArchive(true);
			}else {
				note.setArchive(false);
			}
			
			
			System.out.println("after delete to 1  "+note);
			noteDao.updateNote(note);
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Note> getNotes(String token) {
	
		try {
			int id=UserToken.tokenVerify(token);
			//User user=userService.getUser(id);
			System.out.println(id);		
			
			List<Note> unArchiveNotes=new ArrayList<>();
			List<Note> listAll=noteDao.getNotes(id);
			
					
					
				
				
				
			
			
			
			
			return listAll;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return null;
	}

	@Override
	public List<Note> getArchiveNotes(String token) {
		// TODO Auto-generated method stub
		try {
			int id=UserToken.tokenVerify(token);
			//User user=userService.getUser(id);
			System.out.println(id);		
			
			List<Note> archiveNotes=new ArrayList<>();
			List<Note> listAll=noteDao.getNotes(id);
			for(int i=0;i<listAll.size();i++) {
				if(listAll.get(i).isArchive()==true) {
					archiveNotes.add(listAll.get(i));
					System.out.println(archiveNotes);
					
					
				}
				
				
			}
			
			
			
			return archiveNotes;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return null;

	}

	@Override
	public void archiveNote(Note note, String token) {
		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);
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
	public void updateDeleteNote(Note note, String token) {
		// TODO Auto-generated method stub
		try {
			int id=UserToken.tokenVerify(token);
			if(note.isTrash()==false) {
				note.setTrash(true);
				}else {
					note.setArchive(true);
				}
				
				
				
				noteDao.updateNote(note);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

	@Override
	public void updateRestoreNote(Note note) {
		try {
			
//			User user=userService.getUser(id);
//			System.out.println(id);
		//	note.setUser(user);

			if(note.isTrash()==false) {
			note.setTrash(true);
			}else {
				note.setTrash(false);
			}
			
			
			System.out.println("after delete to 1  "+note);
			noteDao.updateNote(note);
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void updateColorNote(Note note) {
		// TODO Auto-generated method stub
		System.out.println("before"+note);
		
		noteDao.updateNote(note);
		System.out.println("after"+note);
		
	}

	@Override
	public void deleteNoteForever(Note note) {
		// TODO Auto-generated method stub
		
		noteDao.deleteNoteForever(note);
		
		
	}

	

}
