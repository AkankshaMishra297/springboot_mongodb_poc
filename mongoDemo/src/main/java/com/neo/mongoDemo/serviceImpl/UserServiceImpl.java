package com.neo.mongoDemo.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.mongoDemo.bean.DashBoardResponse;
import com.neo.mongoDemo.bean.UserDetailsBean;
import com.neo.mongoDemo.common.CommonConstants;
import com.neo.mongoDemo.model.UserDetails;
import com.neo.mongoDemo.repository.UserRepository;
import com.neo.mongoDemo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveUser(UserDetailsBean userBean) throws Exception {
		
		LOGGER.trace("Starting addUser() from UserServiceImpl with arguments:: dashboardRequest: "+userBean);
		String returnValue = null;
		String errorMsg = null;
		DashBoardResponse dashboardResponse = new DashBoardResponse();
		try {
			UserDetails user = modelMapper.map(userBean, new TypeToken<UserDetails>() {}.getType());
			user.setCreatedDate(new Date());
			UserDetails user1 = this.userRepo.save(user);

			if(user1 != null) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData("info"," User saved" );
			} else
				errorMsg = "No Records found for requested input.";
		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting addUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String editUser(ObjectId id, UserDetailsBean userBean) throws Exception {
		
		LOGGER.trace("Starting editUser() from UserServiceImpl with arguments:: dashboardRequest: "+userBean);
		String returnValue = null;
		String errorMsg = null;
		DashBoardResponse dashboardResponse = new DashBoardResponse();
		try {
			UserDetails user = modelMapper.map(userBean, new TypeToken<UserDetails>() {}.getType());

			user.setId(id);
			UserDetails user1 = this.userRepo.save(user);

			if(user1 != null) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData("info"," User saved" );
			} else
				errorMsg = "No Records found for requested input.";
		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting editUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String getAllUsers() throws Exception {
		
		LOGGER.trace("Starting getAllUser() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashBoardResponse dashboardResponse = new DashBoardResponse();
		try {

			List<UserDetails> user = this.userRepo.findAll();
			List<UserDetailsBean> userList = modelMapper.map(user, new TypeToken<List<UserDetailsBean>>() {}.getType());

			if(user != null) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				dashboardResponse.setResponseData("info", userList );
			} else
				errorMsg = "No Records found for requested input.";
		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting getAllUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}

	@Override
	public String deleteUser(ObjectId id) throws Exception {
		
		LOGGER.trace("Starting deleteUser() from UserServiceImpl");
		String returnValue = null;
		String errorMsg = null;
		DashBoardResponse dashboardResponse = new DashBoardResponse();
		try {

			UserDetails user = userRepo.findById(id);

			if(user != null) {
				dashboardResponse.setStatusCode(CommonConstants.SUCCESS);
				userRepo.delete(user);
				dashboardResponse.setResponseData("info"," User deleted" );
			} else
				errorMsg = "No Records found for requested input.";
		} catch (Exception e) {
			errorMsg = "Following exception occur while fetching User Details.";
			LOGGER.error(errorMsg + "\n\r : "+e.getStackTrace());
			e.printStackTrace();
		}
		if(errorMsg != null){
			dashboardResponse.setStatusCode(CommonConstants.FAIL);
			dashboardResponse.setErrorMsg(errorMsg);
		}
		returnValue = MAPPER.writeValueAsString(dashboardResponse);
		LOGGER.trace("Exiting deleteUser() from UserServiceImpl with return:: returnValue: "+returnValue);
		return returnValue;
	}
}
