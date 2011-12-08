package com.vaannila.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle")
public class Vehicle {

	private int id;
	private String manufacturer;
	private String model; 
	private String productionDate;
	private String registrationNumber;
	private String registrationExpireDate;
	
	@Id
	@GeneratedValue
	@Column(name="Vehicle_ID")
	public int getId() {
		return id;
	}
	public void setId(int id2) {
		this.id = id2;
	}
	
	@Column(name="Vehicle_Manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Column(name="Vehicle_Model")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name="Vehicle_Production_Date")
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	
	@Column(name="Vehicle_Registration_Number")
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	@Column(name="Vehicle_Registration_Expire_Date")
	public String getRegistrationExpireDate() {
		return registrationExpireDate;
	}
	public void setRegistrationExpireDate(String registrationExpireDate) {
		this.registrationExpireDate = registrationExpireDate;
	}
	
	
}
