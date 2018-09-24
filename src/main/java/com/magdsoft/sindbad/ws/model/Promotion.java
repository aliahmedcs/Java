package com.magdsoft.sindbad.ws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Promotion {
	private String code;
	private Integer countNow;
	private Date expDate;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer intialCount;
	private Integer point;
	@ManyToMany (cascade=javax.persistence.CascadeType.ALL,targetEntity=User.class)
//	@JoinTable(name="user_promotion",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="promotion_id")})
	 
	private List<User> user_id = new ArrayList<>();
	
	
	public String getCode() {
		return code;
	}
	public Integer getCountNow() {
		return countNow;
	}
	public Date getExpDate() {
		return expDate;
	}
	public Integer getId() {
		return id;
	}
	public Integer getIntialCount() {
		return intialCount;
	}
	public Integer getPoint() {
		return point;
	}
	public List<User> getUser_id() {
		return user_id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCountNow(Integer countNow) {
		this.countNow = countNow;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIntialCount(Integer intialCount) {
		this.intialCount = intialCount;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public void setUser_id(List<User> user_id) {
		this.user_id = user_id;
	}
	
}
