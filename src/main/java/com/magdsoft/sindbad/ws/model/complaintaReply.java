package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class complaintaReply {
	@ManyToOne
	private Admins admin_id ;
	@ManyToOne
	private UserComplaint complaints_id;
	@CreationTimestamp
	private Date created_at ;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    @Column(columnDefinition="text")
	private String message ;
    @UpdateTimestamp
	private Date updated_at ;
	public Admins getAdmin_id() {
		return admin_id;
	}
	public UserComplaint getComplaints_id() {
		return complaints_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Integer getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setAdmin_id(Admins admin_id) {
		this.admin_id = admin_id;
	}
	public void setComplaints_id(UserComplaint complaints_id) {
		this.complaints_id = complaints_id;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
    
    
}
