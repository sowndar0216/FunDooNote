package com.bridgeit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Label;
import com.bridgeit.model.Note;
import com.bridgeit.model.Response;
import com.bridgeit.service.ILabelService;
import com.bridgeit.utility.UserToken;

@RestController
public class LabelController {

	@Autowired
	private ILabelService labelService;

	@RequestMapping(value = "/addlabel")
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

	@RequestMapping(value = "/getLabel")
	public ResponseEntity<List<Label>> getLabelName(@RequestHeader("token") String token) {

		List<Label> getnotes = labelService.getLabel(token);
		System.out.println("controller" + getnotes);
		return new ResponseEntity<List<Label>>(getnotes, HttpStatus.OK);

	}

}
