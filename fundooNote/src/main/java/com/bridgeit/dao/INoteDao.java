package com.bridgeit.dao;

import java.util.List;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

public interface INoteDao {

	void addNote(Note note);

	void deleteNote(Note note);

	int updateNote(Note note);
	
	
	List<Note> getNotes(int id);

}
