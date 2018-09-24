package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class FreeRide {
	@CreationTimestamp
	private Date createdAt=new Date();
	private Date expiredDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Boolean isUsed;
	private int moneyValue;
	
	private String name;
	private int points;
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = User.class)
	private User user_id;//=new ArrayList<User>();
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public int getId() {
		return id;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public int getMoneyValue() {
		return moneyValue;
	}
	public String getName() {
		return name;
	}
	public int getPoints() {
		return points;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public void setMoneyValue(int moneyValue) {
		this.moneyValue = moneyValue;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

}
