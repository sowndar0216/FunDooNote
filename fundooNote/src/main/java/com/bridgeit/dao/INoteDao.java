package com.bridgeit.dao;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

public interface INoteDao {

	void addNote(Note note, User user);

	void deleteNote(Integer id);

}
