package com.neo.mongoDemo.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserDetails {

	@Id
	private ObjectId id;
	
	private String name;

	private int age;
	
	private String email;
	
	private double salary;

	private List<Address> address;
	
	private Date createdDate;

	public UserDetails() {
		super();
	}

	public UserDetails(String name, int age, String email, double salary, List<Address> address) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
		this.salary = salary;
		this.address = address;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", salary=" + salary
				+ ", address=" + address + ", createdDate=" + createdDate + "]";
	}
	
	

	

}
