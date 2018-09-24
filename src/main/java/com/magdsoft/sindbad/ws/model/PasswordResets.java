package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

public class PasswordResets {
	@CreationTimestamp
	private Date created_at;

	private String email ;
	private String token ;
	public Date getCreated_at() {
		return created_at;
	}
	public String getEmail() {
		return email;
	}
	public String getToken() {
		return token;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
