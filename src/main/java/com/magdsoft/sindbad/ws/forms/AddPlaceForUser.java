package com.magdsoft.sindbad.ws.forms;

public class AddPlaceForUser {
	private String category;
	private String latitude;
	private String longitude;
	private String name;
	private int placeId;
	private Integer userId;
	
	
	public String getCategory() {
		return category;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public String getName() {
		return name;
	}
	public int getPlaceId() {
		return placeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
