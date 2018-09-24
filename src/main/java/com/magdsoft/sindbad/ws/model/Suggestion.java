package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Suggestion {
	@ManyToOne
	private Admins assigned_by;
	@ManyToOne
	private Admins assigned_to ;
	@CreationTimestamp
	private Date createdAt;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(columnDefinition = "TEXT")
	private String suggest;
	private Date updatedAt;
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
	private User user_id; 
	
	
	public Admins getAssigned_by() {
		return assigned_by;
	}

	public Admins getAssigned_to() {
		return assigned_to;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getId() {
		return id;
	}

	public String getSuggest() {
		return suggest;
	}

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setAssigned_by(Admins assigned_by) {
		this.assigned_by = assigned_by;
	}

	
	public void setAssigned_to(Admins assigned_to) {
		this.assigned_to = assigned_to;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

}
