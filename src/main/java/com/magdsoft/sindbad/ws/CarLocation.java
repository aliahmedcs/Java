package com.magdsoft.sindbad.ws;

public class CarLocation implements Comparable<CarLocation> {
	private int carId;
	private double latitude;
	private double longitude;
	public CarLocation() {
		
	}
	public CarLocation(int carId, double latitude, double longitude) {
		super();
		this.carId = carId;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	@Override
	public int compareTo(CarLocation o) {
		if (this.longitude < o.longitude) return -1;
		if (this.longitude > o.longitude) return 1;
		if (this.latitude < o.latitude) return -1;
		if (this.latitude > o.latitude) return 1;
		if (this.carId < o.carId) return -1;
		if (this.carId > o.carId) return 1;
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
		CarLocation other = (CarLocation) obj;
		if (carId != other.carId)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	public int getCarId() {
		return carId;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
