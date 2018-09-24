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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Car {
	@OneToMany(mappedBy="car_id",cascade = CascadeType.ALL,targetEntity = DriverCar.class)
	private List<DriverCar> car_driver=new ArrayList<DriverCar>();
	private int carDriver;
	private String carNumber;
	//	@ManyToOne(cascade=CascadeType.ALL,targetEntity=CarType.class)
//	private CarType currentCarType;
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=CarType.class)
	private CarType carType;
	@Column(unique=true)
	private String chassisNumber;
	private String color;
	@CreationTimestamp
	private Date createAt;
	private Date endAt;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private boolean isActive;
	private boolean isOnLine;
	private Double latitude;
	private String licenseNumber;
	private String licenses;
	private Double longitude;
	private String manufacturer;
	private String model;
private String name;
	private Date startAt;
	private Date updateAt;
	private int year;
	
	public List<DriverCar> getCar_driver() {
		return car_driver;
	}
	public int getCarDriver() {
		return carDriver;
	}
	public String getCarNumber() {
		return carNumber;
	}
	/*public CarType getCurrentCarType() {
		return currentCarType;
	}
	public void setCurrentCarType(CarType currentCarType) {
		this.currentCarType = currentCarType;
	}*/
	public CarType getCarType() {
		return carType;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public String getColor() {
		return color;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public Date getEndAt() {
		return endAt;
	}
	public int getId() {
		return id;
	}
	public Double getLatitude() {
		return latitude;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public String getLicenses() {
		return licenses;
	}
	public Double getLongitude() {
		return longitude;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getModel() {
		return model;
	}
	public String getName() {
		return name;
	}
	public Date getStartAt() {
		return startAt;
	}
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateAt() {
		return updateAt;
	}
	public int getYear() {
		return year;
	}
	public boolean isActive() {
		return isActive;
	}
	public boolean isOnLine() {
		return isOnLine;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setCar_driver(List<DriverCar> car_driver) {
		this.car_driver = car_driver;
	}
	public void setCarDriver(int carDriver) {
		this.carDriver = carDriver;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public void setLicenses(String licenses) {
		this.licenses = licenses;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOnLine(boolean isOnLine) {
		this.isOnLine = isOnLine;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	

}
