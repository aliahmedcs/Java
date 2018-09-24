package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CarType {
	 private float basefare;
	 @CreationTimestamp
	 private Date createdAt;
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int id;
	 private Boolean is_active;
	 private float KMPrice;
	 private float minutePrice;
	 private String  name;
	 private Date updatesAt;
	 
	public float getBasefare() {
		return basefare;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public int getId() {
		return id;
	}
	public Boolean getIs_active() {
		return is_active;
	}
	public float getKMPrice() {
		return KMPrice;
	}
	public float getMinutePrice() {
		return minutePrice;
	}
	public String getName() {
		return name;
	}
	public Date getUpdatesAt() {
		return updatesAt;
	}
	public void setBasefare(float basefare) {
		this.basefare = basefare;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public void setKMPrice(float kMPrice) {
		KMPrice = kMPrice;
	}
	public void setMinutePrice(float minutePrice) {
		this.minutePrice = minutePrice;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUpdatesAt(Date updatesAt) {
		this.updatesAt = updatesAt;
	}
	 
}
