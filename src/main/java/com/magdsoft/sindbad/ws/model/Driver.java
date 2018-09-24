package com.magdsoft.sindbad.ws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Driver {
	
	/*@Transient
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	
	public enum MilitaryStatus{Adjourned,excempted,performed}
    public enum SocialStatus{divorced,married,single}
    private Integer activationCode;
    private String address;
    private String age;
    @CreationTimestamp
    private Date createdAt;
    private String CriminalStatus;
    @OneToMany(mappedBy="driver_id",cascade = CascadeType.ALL,targetEntity = DriverCar.class)
	 private List<DriverCar> driver_car=new ArrayList<DriverCar>();
    @Column(unique=true)
    private String email;
    private Date endAt;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(unique=true)
    private String idNumber;
    private boolean isActive;
    private String job;
    private String licenes;
 
     private String military_doc;
     @Column(columnDefinition="varchar")
//    @Enumerated(EnumType.STRING)
    private MilitaryStatus militaryStatus;
     private String name;
    private String password;
    @Column(unique=true)
    private String phone;
    private String profileImage;
    @Column(columnDefinition="varchar")
//    @Enumerated(EnumType.STRING)
    public SocialStatus socialStatus;
    
    
   /* @OneToMany(cascade = CascadeType.ALL,targetEntity = Profit.class)
   	@JoinColumn(name = "profit_id")
       private List<Profit> profit_id=new ArrayList<Profit>();*/
    
   
	private Date startAt;
	@UpdateTimestamp
    private Date updatedAt;
	public Integer getActivationCode() {
		return activationCode;
	}
	public String getAddress() {
		return address;
	}
	public String getAge() {
		return age;
	}
    public Date getCreatedAt() {
		return createdAt;
	}
    
  
  
	public String getCriminalStatus() {
		return CriminalStatus;
	}
	public List<DriverCar> getDriver_car() {
		return driver_car;
	}
	public String getEmail() {
		return email;
	}
	public Date getEndAt() {
		return endAt;
	}
	public int getId() {
		return id;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public String getJob() {
		return job;
	}
	public String getLicenes() {
		return licenes;
	}
	public String getMilitary_doc() {
		return military_doc;
	}
	public MilitaryStatus getMilitaryStatus() {
		return militaryStatus;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public SocialStatus getSocialStatus() {
		return socialStatus;
	}
	public Date getStartAt() {
		return startAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActivationCode(Integer activationCode) {
		this.activationCode = activationCode;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setCriminalStatus(String criminalStatus) {
		CriminalStatus = criminalStatus;
	}
	public void setDriver_car(List<DriverCar> driver_car) {
		this.driver_car = driver_car;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
   
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public void setLicenes(String licenes) {
		this.licenes = licenes;
	}
	public void setMilitary_doc(String military_doc) {
		this.military_doc = military_doc;
	}
	public void setMilitaryStatus(MilitaryStatus militaryStatus) {
		this.militaryStatus = militaryStatus;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public void setSocialStatus(SocialStatus socialStatus) {
		this.socialStatus = socialStatus;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	} 
}
