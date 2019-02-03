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
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Note;
import com.bridgeit.model.Response;
import com.bridgeit.model.User;
import com.bridgeit.service.INoteService;
import com.bridgeit.service.IUserService;

@RestController
	@CrossOrigin(origins = { "http://localhost:4200" }, exposedHeaders = { "token" })
public class NoteController {

	Response respone;
	User tempUser;
	@Autowired
   private	IUserService userService;
	
	@Autowired
	private INoteService noteService;
	
	@RequestMapping(value="/addNote",method=RequestMethod.POST)
	public ResponseEntity<Response> addNote(@RequestBody Note note,@RequestHeader("token") String token){
		
	System.out.println("note"+note);
		System.out.println(token);
		
		noteService.addNote(note, token);
		
		//tempUser=userService.getUser(note.getUser().getId());
		
		System.out.println(note.getUser().getId());
		//noteService.addNote(note,tempUser);
		respone=new Response();
		respone.setStatusCode(166);
		
	    respone.setStatus("note added");
		
	    
	    return new ResponseEntity<Response>(respone,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/deleteNote",method=RequestMethod.POST)
	public ResponseEntity<Response> deleteUser(@RequestBody Note note,@RequestHeader("token") String token) {
		
		
		//tempUser=userService.getUser(note.getUser().getId());
	
		//noteService.deleteNote(note,token);
		
		System.out.println("delete to 1");
		noteService.updateDeleteNote(note,token);	
		respone = new Response();
		
		
		respone.setStatusCode(166);
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value="/deleteNoteForever",method=RequestMethod.POST)
	public ResponseEntity<Response> deleteNoteForever(@RequestBody Note note) {
		
		
		//tempUser=userService.getUser(note.getUser().getId());
	
		//noteService.deleteNote(note,token);
		
		System.out.println("delete to 1");
		noteService.deleteNoteForever(note);	
		respone = new Response();
		
		
		respone.setStatusCode(166);
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}
	
	
	
	
	
	
	@RequestMapping(value="/updateNote",method=RequestMethod.POST)
	public ResponseEntity<Response> update(@RequestBody Note note,@RequestHeader("token") String token)
	{
	
		System.out.println("enter"+note);
		noteService.updateNote(note,token);	
	respone=new Response();
	respone.setStatusCode(166);
	
return new ResponseEntity<Response>(respone,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getNote",method=RequestMethod.GET)
	public  ResponseEntity<List<Note>> getNotes(@RequestHeader("token") String token){
			
		List<Note> getnotes=noteService.getNotes(token);		
		
		 return new ResponseEntity<List<Note>>(getnotes,HttpStatus.OK);
	}
	
	
	

	
	
	
	@RequestMapping(value="/updateRestoreNote",method=RequestMethod.POST)
	public ResponseEntity<Response> updateRestoreNote(@RequestBody Note note)
	{
	
		System.out.println("restore"+note);
		noteService.updateRestoreNote(note);	
	respone=new Response();
	respone.setStatusCode(166);
	
return new ResponseEntity<Response>(respone,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateColorNote",method=RequestMethod.POST)
	public ResponseEntity<Response> updateColorNote(@RequestBody Note note)
	{
	
		System.out.println("restore"+note);
		noteService.updateColorNote(note);	
	respone=new Response();
	respone.setStatusCode(166);
	
return new ResponseEntity<Response>(respone,HttpStatus.OK);
	}
	
	
	
}
