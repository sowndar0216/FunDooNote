package com.bridgeit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Note;
import com.bridgeit.model.Response;
import com.bridgeit.model.User;
import com.bridgeit.service.INoteService;
import com.bridgeit.service.IUserService;

@RestController
//@CrossOrigin(origins = { "*" }, exposedHeaders = {"token" })
public class NoteController {

	Response respone;
	User tempUser;
	@Autowired
	private IUserService userService;

	@Autowired
	private INoteService noteService;

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<Response> addNote(@RequestBody Note note, @RequestHeader("token") String token) {

		System.out.println("new note   "+note);
		
		noteService.addNote(note, token);
		respone = new Response();
		respone.setStatusCode(166);
		respone.setStatus("note added");

		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}

	@RequestMapping(value = "/note/delete/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> deleteUser(@PathVariable int id, @RequestHeader("token") String token) {

		respone = new Response();
		Note note = noteService.getNoteById(id);
		boolean check = noteService.verifyUser(token, note);
		if (check) {
			noteService.updateTrashNote(note);
			respone.setStatusCode(166);
			return new ResponseEntity<Response>(respone, HttpStatus.OK);
		}
		respone.setStatusCode(404);
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}

	@RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteNoteForever(@PathVariable int id, @RequestHeader("token") String token) {

		respone = new Response();

		Note note = noteService.getNoteById(id);
		boolean check = noteService.verifyUser(token, note);
		if (check) {
			noteService.deleteNoteForever(note);
			respone.setMessage("Note is Deleted ");
			respone.setStatusCode(166);
			return new ResponseEntity<Response>(respone, HttpStatus.OK);
		}
		respone.setStatusCode(404);

		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}

	@RequestMapping(value = "/note/archive/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> update(@PathVariable("id") int id, @RequestHeader("token") String token) {

		
		Note note = noteService.getNoteById(id);
		
		boolean check = noteService.verifyUser(token, note);
	
		respone = new Response();

		if (check) {
			 noteService.updateArchiveNote(note);
			respone.setStatusCode(166);
			respone.setMessage("update successflly");

			return new ResponseEntity<Response>(respone, HttpStatus.OK);
		}
		respone.setStatusCode(404);

		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}

	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getNotes(@RequestHeader("token") String token) {

		List<Note> getnotes = noteService.getNotes(token);

		return new ResponseEntity<List<Note>>(getnotes, HttpStatus.OK);
	}
//update edited note
	@RequestMapping(value = "/note", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateEditNote(@RequestBody Note note, @RequestHeader("token") String token) {

		respone = new Response();
		boolean check = noteService.verifyUser(token, note);

		if (check) {
			noteService.updateEditNote(note);

			respone.setStatusCode(166);
			respone.setMessage("updated..");
			return new ResponseEntity<Response>(respone, HttpStatus.OK);
		}
		respone.setStatusCode(404);
		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}

	@RequestMapping(value = "/note/updateRestoreNote", method = RequestMethod.POST)
	public ResponseEntity<Response> updateRestoreNote(@RequestBody Note note) {

		System.out.println("restore" + note);
		noteService.updateRestoreNote(note);
		respone = new Response();
		respone.setStatusCode(166);

		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}

//	@RequestMapping(value = "/note/color/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Response> updateColorNote(@PathVariable int id, @RequestHeader("token") String token) {
//		Note note = noteService.getNoteById(id);
//		System.out.println("restore" + note);
//		boolean check = noteService.verifyUser(token, note);
//		
//		respone = new Response();
//
//		if (check) {
//			noteService.updateColorsNote(note);
//			respone.setMessage("updated");
//			respone.setStatusCode(166);
//
//			return new ResponseEntity<Response>(respone, HttpStatus.OK);
//
//		}
//		respone.setStatusCode(404);
//
//		return new ResponseEntity<Response>(respone, HttpStatus.OK);
//	}
	
	
	
	@RequestMapping(value = "/note/pin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updatePinNote(@PathVariable int id, @RequestHeader("token") String token) {
		Note note = noteService.getNoteById(id);
		System.out.println("restore" + note);
		boolean check = noteService.verifyUser(token, note);
		respone = new Response();

		if (check) {
			noteService.updatePinNote(note);
			respone.setMessage("updated");
			respone.setStatusCode(166);

			return new ResponseEntity<Response>(respone, HttpStatus.OK);

		}
		respone.setStatusCode(404);

		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}
	
	@RequestMapping(value="search",method =RequestMethod.GET)
	public ResponseEntity<List<Note>> searchNote( @RequestHeader("token") String token,@RequestParam String noteName){
		
		
List<Note> noteList=null;
		return new ResponseEntity <List<Note>>(noteList, HttpStatus.OK);


		
	
	
	
	
	}

}
