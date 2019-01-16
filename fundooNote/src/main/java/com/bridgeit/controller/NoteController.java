package com.bridgeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Note;
import com.bridgeit.model.Response;
import com.bridgeit.model.User;
import com.bridgeit.service.INoteService;
import com.bridgeit.service.IUserService;

@RestController
public class NoteController {

	Response respone;
	User tempUser;
	@Autowired
   private	IUserService userService;
	
	@Autowired
	private INoteService noteService;
	
	@RequestMapping("/addNote/{id}")
	public ResponseEntity<Response> addNote(@RequestBody Note note,@PathVariable Integer  id){
		
		tempUser=userService.getUser(id);
		
		
		noteService.addNote(note,tempUser);
		respone=new Response();
	    respone.setStatus("note added");
		
	    
	    return new ResponseEntity<Response>(respone,HttpStatus.OK);
	}
	
	@RequestMapping("/delete/{id}/{noteId}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer id,@PathVariable Integer noteId) {
		tempUser=userService.getUser(id);
		if(tempUser==null) {
		noteService.deleteNote(noteId);
		}respone = new Response();
		respone.setStatus("deleted");
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}
}
