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
public class DriverCar {
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=Car.class)
	   private Car car_id;
   @CreationTimestamp
   private Date createdAt;
   @ManyToOne(cascade=CascadeType.ALL,targetEntity=Driver.class)
   private Driver driver_id;
   private Date endAt;
   @Id
@GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
   private boolean isActive;
   private Date starteAt;
   private Date updatesAt;
public Car getCar_id() {
	return car_id;
}
public Date getCreatedAt() {
	return createdAt;
}
public Driver getDriver_id() {
	return driver_id;
}
public Date getEndAt() {
	return endAt;
}
public int getId() {
	return id;
}
public Date getStarteAt() {
	return starteAt;
}
@UpdateTimestamp
@Temporal(TemporalType.TIMESTAMP)
public Date getUpdatesAt() {
	return updatesAt;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}
public void setCar_id(Car car_id) {
	this.car_id = car_id;
}
public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}
public void setDriver_id(Driver driver_id) {
	this.driver_id = driver_id;
}
public void setEndAt(Date endAt) {
	this.endAt = endAt;
}
public void setId(int id) {
	this.id = id;
}
public void setStarteAt(Date starteAt) {
	this.starteAt = starteAt;
}
public void setUpdatesAt(Date updatesAt) {
	this.updatesAt = updatesAt;
}
   
    
}
