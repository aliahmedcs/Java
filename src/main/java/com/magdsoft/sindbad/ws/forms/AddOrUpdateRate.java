package com.magdsoft.sindbad.ws.forms;

public class AddOrUpdateRate {
	private int rate;
	private int tripId;
	private int userId;
	public int getRate() {
		return rate;
	}
	public int getTripId() {
		return tripId;
	}
	public int getUserId() {
		return userId;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
