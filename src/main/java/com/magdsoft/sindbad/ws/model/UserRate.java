package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class UserRate {
	@CreationTimestamp
	private Date createdAt;
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = Driver.class)
    private Driver driver_id;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; 
	private int rateValue;
	@OneToOne
    private UserTrip trip_id;
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = User.class)
	private User user_id;
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public Driver getDriver_id() {
		return driver_id;
	}
	public int getId() {
		return id;
	}
	public int getRateValue() {
		return rateValue;
	}
	public UserTrip getTrip_id() {
		return trip_id;
	}
	public User getUser_id() {
		return user_id;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setDriver_id(Driver driver_id) {
		this.driver_id = driver_id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setRateValue(int rateValue) {
		this.rateValue = rateValue;
	}
	public void setTrip_id(UserTrip trip_id) {
		this.trip_id = trip_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	
	
}
