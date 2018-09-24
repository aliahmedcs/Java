package com.magdsoft.sindbad.ws.forms;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class TimedLocation {
	@NotNull
	private Double latitude;
	
	@NotNull
	private Double longitude;
	
	private Date time;

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Date getTime() {
		return time;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
