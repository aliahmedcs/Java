package com.magdsoft.sindbad.ws.forms;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ReportComplaint {
    String complaintDescription;
    int complaintTypeId;
    List<MultipartFile> image;
     int tripId;
     int userId;
    
	

	public String getComplaintDescription() {
		return complaintDescription;
	}
	public int getComplaintTypeId() {
		return complaintTypeId;
	}
	public List getImage() {
		return image;
	}
	public int getTripId() {
		return tripId;
	}
	public int getUserId() {
		return userId;
	}
	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}
	public void setComplaintTypeId(int complaintTypeId) {
		this.complaintTypeId = complaintTypeId;
	}
	public void setImage(List image) {
		this.image = image;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
