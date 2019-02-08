package com.bridgeit.dao;

import java.util.List;

import com.bridgeit.model.Note;


public interface INoteDao {

	void addNote(Note note);

	void deleteNote(Note note);

	boolean updateNote(Note note);
	
	
	List<Note> getNotes(int id);

	void deleteNoteForever(Note note);

	Note getNoteById(int id);

}
