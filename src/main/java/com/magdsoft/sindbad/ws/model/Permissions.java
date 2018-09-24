package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Permissions {
	@CreationTimestamp
	private Date created_at ;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;

	private Integer parent_id ;
	private String permission ;
	@UpdateTimestamp
	private Date updated_at ;
	public Date getCreated_at() {
		return created_at;
	}
	public Integer getId() {
		return id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public String getPermission() {
		return permission;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
