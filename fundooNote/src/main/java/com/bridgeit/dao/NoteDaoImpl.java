package com.bridgeit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

@Repository
public class NoteDaoImpl implements INoteDao{


	@Autowired
	private SessionFactory sessionFactory;
	

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public User getUser(Integer id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}
	
	
	
	@Override
	public void addNote(Note note, User user) {
	
		
		note.setUser(user);
	 

		getCurrentSession().save(note);
	}


	@Override
	public void deleteNote(Integer id) {
		// TODO Auto-generated method stub
	User user=getUser(id);
	if (user != null) {
		getCurrentSession().delete(user);
	}
	}
	
}
