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
public class UserSearch {
	 @CreationTimestamp
     private Date createdAt;
     @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
     private int id;
     private String latitude;
     private String longitude;
     private String search;
     @UpdateTimestamp
 	@Temporal(TemporalType.TIMESTAMP)
     private Date updatedAt;
     @ManyToOne (cascade=CascadeType.ALL,targetEntity=User.class)
     private User user_id;
	public Date getCreatedAt() {
		return createdAt;
	}
	public int getId() {
		return id;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	
	public String getSearch() {
		return search;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public User getUser_id() {
		return user_id;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public void setSearsh(String search) {
		this.search = search;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
     
     
}
