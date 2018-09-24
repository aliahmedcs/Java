package com.magdsoft.sindbad.ws.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
//@Table(name="USER")
public class User {
	private Integer activationCode;
	private Date createdAt=new Date();
	@Column(unique=true)
	private String email;
	private String friendCode;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Boolean isActive;
	
	private String name;
	private String password;
	
	private Integer paymentMethod;
	@Column(unique=true)
	private String phone;

	private Integer points;
	@ManyToMany (cascade=javax.persistence.CascadeType.ALL,targetEntity=Promotion.class)
//  @JoinTable(name="user_promotion",joinColumns={@JoinColumn(name="promotion_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
	
	private List<Promotion> promotion_id = new ArrayList<>();
	private Date updatedAt=new Date();
	private String userCode;
	private String userImage;

	
	
	public Integer getActivationCode() {
		return activationCode;
	}
	@CreationTimestamp
	public Date getCreatedAt() {
		return createdAt;
	}
	@Column(unique=true)
	public String getEmail() {
		return email;
	}
	public String getFriendCode() {
		return friendCode;
	}
	public int getId() {
		return id;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	@Column(unique=true)
	public String getPhone() {
		return phone;
	}
	
	public Integer getPoints() {
		return points;
	}
	public List<Promotion> getPromotion_id() {
		return promotion_id;
	}
	
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getUserImage() {
		return userImage;
	}
	public Boolean isActive() {
		if(isActive==null)
			isActive=false;
		return isActive;
	}
	public void setActivationCode(int activationCode) {
		this.activationCode = activationCode;
	}
	public void setActivationCode(Integer activationCode) {
		this.activationCode = activationCode;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFriendCode(String friendCode) {
		this.friendCode = friendCode;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public void setPromotion_id(List<Promotion> promotion_id) {
		this.promotion_id = promotion_id;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	
	
}
