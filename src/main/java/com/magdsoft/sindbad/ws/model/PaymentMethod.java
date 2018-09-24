package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class PaymentMethod {
	//private String categeory;
	private enum Category{driver,user}

	private Category category;

	@CreationTimestamp
	private Date created_at ;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Boolean is_active;
	private String name;;
	@UpdateTimestamp
	private Date updated_at ;

	
	public Category getCategory() {
		return category;
	}



	public Date getCreated_at() {
		return created_at;
	}



	public Integer getId() {
		return id;
	}



	public Boolean getIs_active() {
		return is_active;
	}



	public String getName() {
		return name;
	}



	public Date getUpdated_at() {
		return updated_at;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}



	

	
	
}
