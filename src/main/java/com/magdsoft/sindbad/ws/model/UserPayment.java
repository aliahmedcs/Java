package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class UserPayment {
	@CreationTimestamp
	private Date createdAt;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String secretKey; 
	@UpdateTimestamp
	private Date updatedAt;
	@ManyToOne
	private User user_id ;
	@ManyToOne
	private PaymentMethod user_payment_id ;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public int getId() {
		return id;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public User getUser_id() {
		return user_id;
	}
	public PaymentMethod getUser_payment_id() {
		return user_payment_id;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public void setUser_payment_id(PaymentMethod user_payment_id) {
		this.user_payment_id = user_payment_id;
	}
}
