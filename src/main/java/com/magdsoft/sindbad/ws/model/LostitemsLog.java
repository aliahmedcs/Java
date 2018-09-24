package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class LostitemsLog {
	private Admins admin_id ;
	@CreationTimestamp
	private Date created_at;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	private LostItem lostitem_id ;
	@Column(columnDefinition="text")
	private String operation;
	@UpdateTimestamp
	private Date updated_at ;
	public Admins getAdmin_id() {
		return admin_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Integer getId() {
		return id;
	}
	public LostItem getLostitem_id() {
		return lostitem_id;
	}
	public String getOperation() {
		return operation;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setAdmin_id(Admins admin_id) {
		this.admin_id = admin_id;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLostitem_id(LostItem lostitem_id) {
		this.lostitem_id = lostitem_id;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
