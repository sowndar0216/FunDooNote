package com.bridgeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Label;
import com.bridgeit.model.Response;
import com.bridgeit.service.ILabelService;

@RestController
public class LabelController {
	
	
	@Autowired
	private ILabelService labelService;
	
	
	
	@RequestMapping(value="/addlabel")
	public ResponseEntity<Response> addlabel(@RequestBody Label label){
		
	System.out.println(label);	
	boolean check=labelService.addLabel(label);
	Response response=new Response();
	if(check) {
		System.out.println("label added");
		
		response.setMessage("added");
	}
	
return new ResponseEntity<Response>(response,HttpStatus.OK);

	}

}
