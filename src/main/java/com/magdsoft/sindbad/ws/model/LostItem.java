package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class LostItem {
	@CreationTimestamp
    private Date createdAt;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private boolean isActive;
    

	@OneToOne
    private UserTrip trip_Id;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
	@ManyToOne(targetEntity=User.class,cascade=CascadeType.ALL)
    User user_id;
    public Date getCreatedAt() {
		return createdAt;
	}
	public int getId() {
		return id;
	}
	
	public UserTrip getTrip_Id() {
		return trip_Id;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public User getUser_id() {
		return user_id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTrip_Id(UserTrip trip_Id) {
		this.trip_Id = trip_Id;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
    
}
