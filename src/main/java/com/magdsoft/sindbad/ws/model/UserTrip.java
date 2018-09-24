package com.magdsoft.sindbad.ws.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class UserTrip {
	public enum Status{
    	canceledByDriver,canceledByUser,driverAccepted,driverArriving,finished,
    	inProgress,noDrivers,oppened,waiting
    }
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=Car.class)
    private Car car_id;
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=CarType.class)
    private CarType car_type;
	private String comment;
	@CreationTimestamp
    private Date createdAt;
    private String currentLatitude;
    private String currentLongitude;
    private int distance;
    private String driver_comment;
    @ManyToOne(cascade=CascadeType.ALL,targetEntity=Driver.class)
    private Driver driver_id;
    private Float driverRate;
    private Date endAt;
    private String endAtAddress;
    private String endAtLatitude;
    private String endAtLongitude;
    private Date endedAt;
    private String estimatedCost;
    private int estimatedDistance;
    private int estimatedEndLatitude;
    private int estimatedEndLongtude;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;;
    private int rate; 
    private String realCost;
    private Date startAt;
    private String startAtAddress;
    private String startAtLatitude;
    private String startAtLongitude;
    private Status status;
    private String tripeCostRate;
    private String user_comment;
    @ManyToOne(cascade=CascadeType.ALL,targetEntity=User.class)
    private User user_id;
    private Float user_rate;
    
    
	public Car getCar_id() {
		return car_id;
	}
	public CarType getCar_type() {
		return car_type;
	}
	public String getComment() {
		return comment;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public String getCurrentLatitude() {
		return currentLatitude;
	}
	public String getCurrentLongitude() {
		return currentLongitude;
	}
	public int getDistance() {
		return distance;
	}
	public String getDriver_comment() {
		return driver_comment;
	}
	public Driver getDriver_id() {
		return driver_id;
	}
	public Float getDriverRate() {
		return driverRate;
	}
	public Date getEndAt() {
		return endAt;
	}
	public String getEndAtAddress() {
		return endAtAddress;
	}
	public String getEndAtLatitude() {
		return endAtLatitude;
	}
	public String getEndAtLongitude() {
		return endAtLongitude;
	}
	public Date getEndedAt() {
		return endedAt;
	}
	public String getEstimatedCost() {
		return estimatedCost;
	}
	public int getEstimatedDistance() {
		return estimatedDistance;
	}
	public int getEstimatedEndLatitude() {
		return estimatedEndLatitude;
	}
	public int getEstimatedEndLongtude() {
		return estimatedEndLongtude;
	}
	public int getId() {
		return id;
	}
	public int getRate() {
		return rate;
	}
	public String getRealCost() {
		return realCost;
	}
	
	public Date getStartAt() {
		return startAt;
	}
	public String getStartAtAddress() {
		return startAtAddress;
	}
	public String getStartAtLatitude() {
		return startAtLatitude;
	}
	public String getStartAtLongitude() {
		return startAtLongitude;
	}
	public Status getStatus() {
		return status;
	}
	public String getTripeCostRate() {
		return tripeCostRate;
	}
	public String getUser_comment() {
		return user_comment;
	}
	public User getUser_id() {
		return user_id;
	}
	public Float getUser_rate() {
		return user_rate;
	}
	public void setCar_id(Car car_id) {
		this.car_id = car_id;
	}
	
	public void setCar_type(CarType car_type) {
		this.car_type = car_type;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setCurrentLatitude(String currentLatitude) {
		this.currentLatitude = currentLatitude;
	}
	public void setCurrentLongitude(String currentLongitude) {
		this.currentLongitude = currentLongitude;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void setDriver_comment(String driver_comment) {
		this.driver_comment = driver_comment;
	}
	public void setDriver_id(Driver driver_id) {
		this.driver_id = driver_id;
	}
	public void setDriverRate(Float driverRate) {
		this.driverRate = driverRate;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	public void setEndAtAddress(String endAtAddress) {
		this.endAtAddress = endAtAddress;
	}
	public void setEndAtLatitude(String endAtLatitude) {
		this.endAtLatitude = endAtLatitude;
	}
	public void setEndAtLongitude(String endAtLongitude) {
		this.endAtLongitude = endAtLongitude;
	}
	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}
	public void setEstimatedCost(String estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public void setEstimatedDistance(int estimatedDistance) {
		this.estimatedDistance = estimatedDistance;
	}
	public void setEstimatedEndLatitude(int estimatedEndLatitude) {
		this.estimatedEndLatitude = estimatedEndLatitude;
	}
	public void setEstimatedEndLongtude(int estimatedEndLongtude) {
		this.estimatedEndLongtude = estimatedEndLongtude;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public void setRealCost(String realCost) {
		this.realCost = realCost;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	public void setStartAtAddress(String startAtAddress) {
		this.startAtAddress = startAtAddress;
	}
	public void setStartAtLatitude(String startAtLatitude) {
		this.startAtLatitude = startAtLatitude;
	}
	public void setStartAtLongitude(String startAtLongitude) {
		this.startAtLongitude = startAtLongitude;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setTripeCostRate(String tripeCostRate) {
		this.tripeCostRate = tripeCostRate;
	}
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public void setUser_rate(Float user_rate) {
		this.user_rate = user_rate;
	}
    
}
