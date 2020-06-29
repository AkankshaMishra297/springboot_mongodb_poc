package com.neo.mongoDemo.controller;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neo.mongoDemo.bean.UserDetailsBean;
import com.neo.mongoDemo.service.UserService;

@RestController
public class UserController extends UserValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	//get users
	@GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody UserDetailsBean dashboardRequest) throws Exception {
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

	//edit user
	@PutMapping(value = "/editUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editUser(@PathVariable("id") ObjectId id, @RequestBody UserDetailsBean dashboardRequest) throws Exception {
		LOGGER.trace("Starting editUser() from UserController with arguments:: dashboardRequest: "+dashboardRequest);
		ResponseEntity<?> responseEntity = null;
		if(validate(dashboardRequest).equals("pass")) {
			String jsonString = userService.editUser(id,dashboardRequest);
			if(jsonString != null){
				responseEntity = ResponseEntity.ok(jsonString);
			} else
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			responseEntity = ResponseEntity.ok(validate(dashboardRequest));
		}
		LOGGER.trace("Exiting editUser() from UserController with return:: responseEntity: "+responseEntity);
		return responseEntity;
	}

	//delete users
	@DeleteMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") ObjectId id) throws Exception {
		LOGGER.trace("Starting deleteUser() from UserController");
		ResponseEntity<?> responseEntity = null;
		String jsonString = userService.deleteUser(id);
		if(jsonString != null){
			responseEntity = ResponseEntity.ok(jsonString);
		} else
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.trace("Exiting deleteUser() from UserController");
		return responseEntity;
	}
}
