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
public class Help {
	@ManyToOne
    private Admins admin_id;
    @Column(columnDefinition="text")
    private String answer;
    @CreationTimestamp
    private Date createdAt;
    @ManyToOne(cascade=CascadeType.ALL,targetEntity=Driver.class)
    private Driver driver_id;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private Integer is_active;
    private boolean isGlobal;
    private String question;
    private String questionTitle;
    private String type;
    private Date updatedAt;
    @ManyToOne(cascade=CascadeType.ALL,targetEntity=User.class)
    private User user_id;
   
    
	public Admins getAdmin_id() {
		return admin_id;
	}
	public String getAnswer() {
		return answer;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Driver getDriver_id() {
		return driver_id;
	}
	public int getId() {
		return id;
	}
	public Integer getIs_active() {
		return is_active;
	}
	public String getQuestion() {
		return question;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public String getType() {
		return type;
	}
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public User getUser_id() {
		return user_id;
	}
	public boolean isGlobal() {
		return isGlobal;
	}
	public void setAdmin_id(Admins admin_id) {
		this.admin_id = admin_id;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setDriver_id(Driver driver_id) {
		this.driver_id = driver_id;
	}
	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
    
}
