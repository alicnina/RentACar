package com.alicnina.policeregistersimulator;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Register {

	private String idNumber;
	private String drivingLicenceNumber;
	
	// getters and setters
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getIdNumber() {
		return idNumber;
	}

	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}

}
