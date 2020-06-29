package com.neo.mongoDemo.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.mongoDemo.bean.DashBoardResponse;
import com.neo.mongoDemo.model.UserDetails;

public class UserValidation {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(UserValidation.class);

	
	public String validate(UserDetails request) throws Exception {
		LOGGER.trace("Starting validate() from UserValidation");

		String returnValue=null;
		DashBoardResponse dashboardResponse = new DashBoardResponse();
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserDetails>> violations = validator.validate(request);
		for (ConstraintViolation<UserDetails> violation : violations) {
		    LOGGER.error(violation.getMessage());
		    dashboardResponse.setResponseData(violation.getPropertyPath().toString() ,violation.getMessage());
		   returnValue = MAPPER.writeValueAsString(dashboardResponse);
		   return returnValue;
		}
		LOGGER.trace("Exiting validate() from UserValidation");
		return "pass";
	}

}
