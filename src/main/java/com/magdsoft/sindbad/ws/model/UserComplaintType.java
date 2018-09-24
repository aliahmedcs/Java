package com.magdsoft.sindbad.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UserComplaintType {
	private String color;
	@Column(columnDefinition="text")
	private String description;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Boolean is_active;
	private String name;
	private String type;
	
	
	public String getColor() {
		return color;
	}
	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public Boolean getIs_active() {
		return is_active;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
