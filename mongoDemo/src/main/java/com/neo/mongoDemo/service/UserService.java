package com.neo.mongoDemo.service;

import com.neo.mongoDemo.model.UserDetails;

public interface UserService {

	public String getAllUsers() throws Exception;

	public String saveUser(UserDetails request) throws Exception;
	

}
