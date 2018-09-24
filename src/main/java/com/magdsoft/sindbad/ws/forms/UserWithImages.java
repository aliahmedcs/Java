package com.magdsoft.sindbad.ws.forms;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

public class UserWithImages {
	@Email
	private String email;
	private Integer id;
	
	private MultipartFile image;
	
	private String name;
	
	@Digits(integer=35, fraction=0)
	private String phone;

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public MultipartFile getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
