package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Admins {
	private Integer active;

	private Integer age;

	@CreationTimestamp
	private Date created_at;

	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String image;

	private String member_id;

	private String name;

	private Integer parent_id;

	private String password;

	private String phone;

	private String position;

	private String remember_token;
	private Integer role;
	@UpdateTimestamp
	private Date updated_at;

	public Integer getActive() {
		return active;
	}

	public Integer getAge() {
		return age;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getName() {
		return name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getPosition() {
		return position;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public Integer getRole() {
		return role;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}
