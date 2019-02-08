package com.bridgeit.service;

import java.util.List;

import com.bridgeit.model.Note;

public interface INoteService {
	public boolean addNote(Note note,String token);

	public void deleteNote(Note Note,String token);

	public boolean updateArchiveNote(Note note);

	public List<Note> getNotes(String token);

	

	public void archiveNote(Note note, String token);

	public boolean updateTrashNote(Note note);

	public void updateRestoreNote(Note note);

	public boolean updateColorsNote(Note note);

	public void deleteNoteForever(Note note);

	public void updateEditNote(Note note);

	public Note getNoteById(int id);

	public boolean verifyUser(String token, Note note);

	public boolean updatePinNote(Note note);
}
