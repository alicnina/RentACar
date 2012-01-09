package com.vaannila.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

	private int id;
	private String manufacturer;
	private String model;
	private String productionDate;
	private String registrationNumber;
	private String registrationExpireDate;
	private String rentPricePerDay;
	private String status;
	private Set<Rental> rental;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Vehicle_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id2) {
		this.id = id2;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
	public Set<Rental> getRentalRecords() {
		return this.rental;
	}

	public void setRentalRecords(Set<Rental> rentalRecords) {
		this.rental = rentalRecords;
	}

	@Column(name = "Vehicle_Manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "Vehicle_Model")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "Vehicle_Production_Date")
	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	@Column(name = "Vehicle_Registration_Number")
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Column(name = "Vehicle_Registration_Expire_Date")
	public String getRegistrationExpireDate() {
		return registrationExpireDate;
	}

	public void setRegistrationExpireDate(String registrationExpireDate) {
		this.registrationExpireDate = registrationExpireDate;
	}
	
	@Column(name = "Vehicle_Rent_Price_Per_Day")
	public String getRentPricePerDay() {
		return rentPricePerDay;
	}

	public void setRentPricePerDay(String rentPricePerDay) {
		this.rentPricePerDay = rentPricePerDay;
	}
	
	@Column(name = "Vehicle_Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
