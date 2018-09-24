package com.magdsoft.sindbad.ws.forms;

public class CancelTrip {
    private Integer cutMoney;
    private Boolean isCanceledByUser;
    private String reason;
    private Integer tripId;
    private Integer userId;
	public Integer getCutMoney() {
		return cutMoney;
	}
	public Boolean getIsCanceledByUser() {
		return isCanceledByUser;
	}
	public String getReason() {
		return reason;
	}
	public Integer getTripId() {
		return tripId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setCutMoney(Integer cutMoney) {
		this.cutMoney = cutMoney;
	}
	public void setIsCanceledByUser(Boolean isCanceledByUser) {
		this.isCanceledByUser = isCanceledByUser;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
}
