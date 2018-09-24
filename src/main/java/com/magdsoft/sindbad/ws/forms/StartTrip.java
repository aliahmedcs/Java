package com.magdsoft.sindbad.ws.forms;

public class StartTrip {
	private Integer driverId;
	private String startAddress;
	private String startLatitude;
	private String startLongitude;
	private Integer tripId;
	public Integer getDriverId() {
		return driverId;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public String getStartLatitude() {
		return startLatitude;
	}
	public String getStartLongitude() {
		return startLongitude;
	}
	public Integer getTripId() {
		return tripId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public void setStartLatitude(String startLatitude) {
		this.startLatitude = startLatitude;
	}
	public void setStartLongitude(String startLongitude) {
		this.startLongitude = startLongitude;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	

}
