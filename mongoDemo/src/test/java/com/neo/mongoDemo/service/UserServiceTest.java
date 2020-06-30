package com.neo.mongoDemo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.mongoDemo.bean.UserDetailsBean;
import com.neo.mongoDemo.model.Address;
import com.neo.mongoDemo.model.UserDetails;
import com.neo.mongoDemo.repository.UserRepository;
import com.neo.mongoDemo.serviceImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepo;

	@Mock
	private ModelMapper modelMapper;

	@Test
	public void selectAllActiveUsers() throws Exception{
		List<UserDetails> expectedResult = Stream
				.of(new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address())), new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address()))).collect(Collectors.toList());

		doReturn(expectedResult).when(userRepo).findAll();

		String actualResult = userService.getAllUsers();

		assertThat(expectedResult).isEqualTo(actualResult);

		verify(userRepo).findAll();

	}		

	@Test
	public void addUser() throws Exception{

		UserDetails userDetails =new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address()));

		doReturn(userDetails).when(userRepo).save(userDetails);

		UserDetails expectedResult = userDetails;

		UserDetailsBean userBean = modelMapper.map(userDetails, new TypeToken<UserDetailsBean>() {}.getType());

		String actualResult = userService.saveUser(userBean);

		assertThat(expectedResult).isEqualTo(actualResult);

		verify(userRepo).save(any());
	}

	@Test
	public void editUser(){
		UserDetails expectedResult =new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address()));

		doReturn(expectedResult).when(userRepo).save(any(UserDetails.class));
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(expectedResult));

		UserDetailsBean userBean = modelMapper.map(expectedResult, new TypeToken<UserDetailsBean>() {}.getType());

		//String actualResult = userService.editUser("2", userBean);

		//assertThat(expectedResult).isEqualTo(actualResult);

		verify(userRepo).save(any());
	}

	@Test
	public void deleteUser(){
		UserDetails user =new UserDetails("Danile", 31, "USA",2000,Arrays.asList(new Address()));

		doNothing().when(userRepo).deleteById(anyInt());
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		//userService.deleteUser(2);
		verify(userRepo).delete(any());
	}
}
