package com.magdsoft.sindbad.ws.model;

import java.util.Date;

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
public class DriverPayment {
	@CreationTimestamp
	private Date createdAt;
	@ManyToOne
	private Driver driver_id;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private PaymentMethod method;
	private String secretKey;
	private Date updatedAt; 
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public Driver getDriver_id() {
		return driver_id;
	}
	public int getId() {
		return id;
	}
	public PaymentMethod getMethod() {
		return method;
	}
	public String getSecretKey() {
		return secretKey;
	}
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
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
	public void setMethod(PaymentMethod method) {
		this.method = method;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
