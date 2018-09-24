package com.magdsoft.sindbad.ws;

public class UserLocation implements Comparable<UserLocation> {
	private double latitude;
	private double longitude;
	private int userId;
	public UserLocation() {
		
	}
	public UserLocation(int userId, double latitude, double longitude) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	@Override
	public int compareTo(UserLocation o) {
		if (this.longitude < o.longitude) return -1;
		if (this.longitude > o.longitude) return 1;
		if (this.latitude < o.latitude) return -1;
		if (this.latitude > o.latitude) return 1;
		if (this.userId < o.userId) return -1;
		if (this.userId > o.userId) return 1;
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLocation other = (UserLocation) obj;
		if (userId != other.userId)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public int getUserId() {
		return userId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
