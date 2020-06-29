package com.neo.mongoDemo.service;


import org.bson.types.ObjectId;

import com.neo.mongoDemo.bean.UserDetailsBean;

public interface UserService {

	public String getAllUsers() throws Exception;

	public String saveUser(UserDetailsBean request) throws Exception;

	public String editUser(ObjectId id, UserDetailsBean dashboardRequest) throws Exception;

	public String deleteUser(ObjectId id) throws Exception;

}
