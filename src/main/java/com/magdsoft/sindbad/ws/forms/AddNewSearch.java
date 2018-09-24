package com.magdsoft.sindbad.ws.forms;

public class AddNewSearch {
	private Integer id;
	private String latitude;
	private String longitude;
	private String searchName;

	public Integer getId() {
		return id;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getSearchName() {
		return searchName;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	

	
}
