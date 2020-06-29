package com.neo.mongoDemo.bean;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.neo.mongoDemo.model.Address;

public class UserDetailsBean {

	@NotNull
	@Pattern(regexp ="([a-zA-Z]){2,16}", message = "invalid name")
	private String name;

	@NotNull
	private int age;
	
	@NotNull
	private double salary;
	
	@NotNull
	@Pattern(regexp ="^([A-Za-z0-9])(([.])?[0-9a-z])*[@]([a-z])+([.]([a-z])+){1,3}", message = "invalid email")
	private String email;

	private List<Address> address;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDetailsBean [name=" + name + ", age=" + age + ", salary=" + salary + ", email=" + email
				+ ", address=" + address + "]";
	}

	
}
