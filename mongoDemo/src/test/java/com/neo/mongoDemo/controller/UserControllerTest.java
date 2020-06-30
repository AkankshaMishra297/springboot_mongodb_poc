package com.neo.mongoDemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.mongoDemo.bean.DashBoardResponse;
import com.neo.mongoDemo.model.Address;
import com.neo.mongoDemo.model.UserDetails;
import com.neo.mongoDemo.serviceImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
    UserServiceImpl userService;


	ObjectMapper om = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
    public void getAllActiveUsers() throws Exception {
		List<UserDetails> userList = Stream
				.of(new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address())), new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address()))).collect(Collectors.toList());

        
        when(userService.getAllUsers())
                .thenReturn(userList.toString());

        String response = om.writeValueAsString(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/getUsers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).getAllUsers();
    }

	@Test
	public void addUserTest() throws Exception {
		UserDetails userDetails =new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address()));

		String jsonRequest = om.writeValueAsString(userDetails);
		MvcResult result = mockMvc.perform(post("/addUser").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		DashBoardResponse response = om.readValue(resultContent, DashBoardResponse.class);
		Assert.assertTrue(response.getStatusCode().equalsIgnoreCase("true"));

	}
//
//	@Test
//	public void getEmployeesTest() throws Exception {
//		MvcResult result = mockMvc
//				.perform(get("/getUsers").content(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		String resultContent = result.getResponse().getContentAsString();
//		DashBoardResponse response = om.readValue(resultContent, DashBoardResponse.class);
//		Assert.assertTrue(response.getStatusCode().equalsIgnoreCase("true"));
//
//	}

}

  