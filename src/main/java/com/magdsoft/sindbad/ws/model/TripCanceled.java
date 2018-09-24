package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class TripCanceled {
	@CreationTimestamp
	private Date createdAt;
	private int cutMoney;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private boolean isCanceledByUser;
	private String reason;
	@OneToOne
	private UserTrip trip_id;
	@UpdateTimestamp
	private Date updatedAt;
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getCutMoney() {
		return cutMoney;
	}

	public int getId() {
		return id;
	}

	public String getReason() {
		return reason;
	}

	/*public UserTrip getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(UserTrip trip_id) {
		this.trip_id = trip_id;
	}*/

	public UserTrip getTrip_id() {
		return trip_id;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public boolean isCanceledByUser() {
		return isCanceledByUser;
	}

	public void setCanceledByUser(boolean isCanceledByUser) {
		this.isCanceledByUser = isCanceledByUser;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setCutMoney(int cutMoney) {
		this.cutMoney = cutMoney;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setTrip_id(UserTrip trip_id) {
		this.trip_id = trip_id;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
