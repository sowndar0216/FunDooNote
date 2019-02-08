package com.bridgeit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Label;
import com.bridgeit.model.Note;
import com.bridgeit.model.Response;
import com.bridgeit.service.ILabelService;
import com.bridgeit.service.INoteService;
import com.bridgeit.utility.UserToken;

@RestController

public class LabelController {

	@Autowired
	private ILabelService labelService;
	

	@Autowired
	private INoteService noteService;


	@RequestMapping(value = "/addlabel", method = RequestMethod.POST)
	public ResponseEntity<Response> addlabel(@RequestBody Label label, @RequestHeader("token") String token) {
		Response response = new Response();
		System.out.println(label);
		try {
			int id = UserToken.tokenVerify(token);
			label.setUserId(id);

			boolean check = labelService.addLabel(label);

			if (check) {
				System.out.println("label added");

				response.setMessage("added");
			}

			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/label", method = RequestMethod.GET)
	public ResponseEntity<List<Label>> getLabelName(@RequestHeader("token") String token) {

		List<Label> getnotes = labelService.getLabel(token);
		System.out.println("controller" + getnotes);
		return new ResponseEntity<List<Label>>(getnotes, HttpStatus.OK);

	}

	@RequestMapping(value = "/label", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateLabel(@RequestBody Label label, @RequestHeader("token") String token) {
		Response response = new Response();

		boolean check = labelService.updateLabel(label, token);
		if (check) {

			response.setStatusCode(166);
			response.setMessage("update sucessfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		}
		response.setStatusCode(404);

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/label/delete/{labelId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteLabel(@PathVariable int labelId, @RequestHeader("token") String token) {
		Label label = labelService.getLabelById(labelId);
		Response response = new Response();
		boolean check = labelService.userVerify(label, token);
		if (check) {

			response.setStatusCode(166);
			response.setMessage("update sucessfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		}
		response.setStatusCode(404);

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/addnotetolabel",method=RequestMethod.POST)
	public ResponseEntity<Response> addNotetoLabel(@RequestParam String noteId,@RequestParam String labelId )
	{
		
		int labelid=Integer.parseInt(labelId);	
		int noteid=Integer.parseInt(noteId);
		Note note=noteService.getNoteById(noteid);
		System.out.println(note);
		Label label=labelService.getLabelById(labelid);
		System.out.println(label);
		Response response=new Response();
		System.out.println();
		response.setMessage("added");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		
	}

}
