package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
public class UserComplaintImage {
	@CreationTimestamp
    private Date createdAt;
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    
    private String image;
    private Date updatedAt;
    @ManyToOne(cascade=CascadeType.ALL,targetEntity=UserComplaint.class)
    private UserComplaint user_complaint;
    
	public Date getCreatedAt() {
		return createdAt;
	}
	public int getId() {
		return id;
	}
	public String getImage() {
		return image;
	}
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	
	
	public UserComplaint getUser_complaint() {
		return user_complaint;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUser_complaint(UserComplaint user_complaint) {
		this.user_complaint = user_complaint;
	}
    
}
