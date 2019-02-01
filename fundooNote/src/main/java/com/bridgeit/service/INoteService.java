package com.bridgeit.service;

import java.util.List;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

public interface INoteService {
	public boolean addNote(Note note,String token);

	public void deleteNote(Note Note,String token);

	public void updateNote(Note note,String token);

	public List<Note> getNotes(String token);

	public List<Note> getArchiveNotes(String token);

	public void archiveNote(Note note, String token);

	public void updateDeleteNote(Note note, String token);
}
