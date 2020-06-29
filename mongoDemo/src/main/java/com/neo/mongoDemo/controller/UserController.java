package com.neo.mongoDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neo.mongoDemo.model.UserDetails;
import com.neo.mongoDemo.service.UserService;

@RestController
public class UserController extends UserValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	//get users
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() throws Exception {
		LOGGER.trace("Starting getAllUsers() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.getAllUsers();
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting getAllUsers() from UserController");
		return responseEntity;
	}
	
	//add user
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody UserDetails dashboardRequest) throws Exception {
		LOGGER.trace("Starting addUser() from UserController with arguments:: dashboardRequest: "+dashboardRequest);
		ResponseEntity<?> responseEntity = null;
		if(validate(dashboardRequest).equals("pass")) {
			String jsonString = userService.saveUser(dashboardRequest);
			if(jsonString != null){
				responseEntity = ResponseEntity.ok(jsonString);
			} else
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = ResponseEntity.ok(validate(dashboardRequest));
		}
		LOGGER.trace("Exiting addUser() from UserController with return:: responseEntity: "+responseEntity);
		return responseEntity;
	}
	
}
