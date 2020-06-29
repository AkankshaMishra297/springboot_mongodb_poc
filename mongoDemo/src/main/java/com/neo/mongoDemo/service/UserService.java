package com.neo.mongoDemo.service;

import com.neo.mongoDemo.model.User;

public interface UserService {

	public String getAllUsers() throws Exception;

	public String saveUser(User request) throws Exception;
	

}
