package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class UserComplaint {
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = UserComplaintType.class)
	private UserComplaintType complaint_type;
	@CreationTimestamp
	private Date createAt;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private boolean isActive;
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = UserTrip.class)
	private UserTrip trip_id;
	private String type;
	private Date updateAt;
	
	public UserComplaintType getComplaint_type() {
		return complaint_type;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public UserTrip getTrip_id() {
		return trip_id;
	}
	public String getType() {
		return type;
	}
	
	
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateAt() {
		return updateAt;
	}
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setComplaint_type(UserComplaintType complaint_type) {
		this.complaint_type = complaint_type;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTrip_id(UserTrip trip_id) {
		this.trip_id = trip_id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}
