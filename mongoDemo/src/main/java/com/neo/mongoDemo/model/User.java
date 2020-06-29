package com.neo.mongoDemo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	
	@Id ObjectId id;

	@NotNull
	@Pattern(regexp ="([A-Za-z0-9]){4,12}", message = "invalid username")
	private String username;

	@NotNull
	private String password;

	private boolean active;

	private String createdAt;

	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", createdAt=" + createdAt + "]";
	}


}
