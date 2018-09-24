package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class AdminPermission {
	private Integer admin_id ;

	@CreationTimestamp
	private Date created_at ;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer permission_id ;
    @UpdateTimestamp
	private Date updated_at ;
	public Integer getAdmin_id() {
		return admin_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Integer getId() {
		return id;
	}
	public Integer getPermission_id() {
		return permission_id;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

    
}
