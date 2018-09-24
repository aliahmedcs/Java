package com.magdsoft.sindbad.ws.forms;

public class EndTrip {
	private int distance;
	private String endLatitude;
	private String endLongtude;
	private String realCost;
	private Integer tripId;
	private Integer userId;
	public int getDistance() {
		return distance;
	}
	public String getEndLatitude() {
		return endLatitude;
	}
	public String getEndLongtude() {
		return endLongtude;
	}
	public String getRealCost() {
		return realCost;
	}
	
	public Integer getTripId() {
		return tripId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void setEndLatitude(String endLatitude) {
		this.endLatitude = endLatitude;
	}
	public void setEndLongtude(String endLongtude) {
		this.endLongtude = endLongtude;
	}
	public void setRealCost(String realCost) {
		this.realCost = realCost;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
