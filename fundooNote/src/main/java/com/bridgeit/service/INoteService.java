package com.bridgeit.service;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

public interface INoteService {
	public boolean addNote(Note note,User user);

	public void deleteNote(Note Note);

	public void updateNote(Note note);
}
