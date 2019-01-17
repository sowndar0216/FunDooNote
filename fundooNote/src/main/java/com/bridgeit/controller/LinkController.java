package com.bridgeit.controller;


import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Response;
import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;
import com.bridgeit.service.IUserService;

@RestController
public class LinkController {
	User tempUser;
	Response respone;

	@Autowired
	private IUserService userService;

	
	
	@RequestMapping(value = "/")
	public String mainPage() {
		return "wellcome";
	}
	
	
	
	@RequestMapping("/login")
	public ResponseEntity<Response> logIn(@RequestBody User user)
	{
	
		
		respone=new Response();
		respone.setStatus("log  in sucessful..");
		
		
		String token=userService.logIn(user);
		
		
		System.out.println(tempUser);
		System.out.println(token);
		HttpHeaders header=new HttpHeaders();
		
		header.add("jwtToken", token);
		
		System.out.println(token);
		System.out.println(header);
		return new ResponseEntity<Response>(respone,header,HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value="/forgetPassword")
	public ResponseEntity<Response> forgetPassword(@RequestBody User user){
		
		System.out.println(user);
		boolean check=userService.reSendOtp(user);
		tempUser=user;
		Response response=new Response();
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/resetPassword")
	public ResponseEntity<Response> resetPassword(@RequestBody UserOtp userOtp){
		
		boolean Check=userService.resetPassword(userOtp);
		
		
		return new ResponseEntity<Response>(HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(value="/verifyEmail/{token:.+}")
	public ResponseEntity<Response> verifyEmail(@PathVariable String token){
		
		Response response=new Response();
		System.out.println(token);
	    userService.verify(token);
	    response.setMessage("verifed");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		
	}
	
	

	@RequestMapping("/add")
	public ResponseEntity<Response> addUser(@RequestBody User user) {

		System.out.println("hello");
		System.out.println(user);
		tempUser = user;
		boolean emailCheck = userService.checkEmail(user);
		respone = new Response();
		if (emailCheck) {
			userService.sendOtp(user);
			respone.setStatus("otp sent");
			return new ResponseEntity<Response>(respone, HttpStatus.OK);
		}
		respone.setStatus("otp not sent");
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

		// teamService.addUser(user);

		// return "email id is in valid";

	}

	@RequestMapping("/view")
	public String view() {
		System.out.println(userService.getUsers());
		return "ok";
	}

	@RequestMapping("/editUser/{id}")
	public String editUser(@PathVariable Integer id, @RequestBody User userCon) {
		System.out.println(id);
		User user = userService.getUser(id);
		System.out.println(user);

		userService.updateUser(user, id, userCon);
		return "update";
	}

	/*
	 * @RequestMapping(value="/index") public ModelAndView indexPage() { return new
	 * ModelAndView("home"); }
	 */
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer id) {

		userService.deleteUser(id);
		respone = new Response();
		respone.setStatus("deleted");
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}

	@RequestMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserOtp otp) {
		// System.out.println("register");
		boolean verifyOtp = userService.verifyOtp(otp);
		// System.out.println(verifyOtp);
		respone = new Response();
		if (verifyOtp) {
			// User user=teamService.getUser();
			// System.out.println("aa");
			// System.out.println(tempUser);
			userService.addUser(tempUser);
			respone.setStatus("user is registered");
			return new ResponseEntity<Response>(respone, HttpStatus.OK);

		}

		respone.setStatus("user is not registered");
		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}

}
