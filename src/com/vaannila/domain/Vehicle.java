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
@Table(name = "vehicle")
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
	@Column(name = "vehicle_id", unique = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
	public Set<Rental> getRentalRecords() {
		return this.rental;
	}

	public void setRentalRecords(Set<Rental> rentalRecords) {
		this.rental = rentalRecords;
	}

	@Column(name = "manufacturer")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "model")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "production_date")
	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	@Column(name = "registration_number")
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Column(name = "registration_expire_date")
	public String getRegistrationExpireDate() {
		return registrationExpireDate;
	}

	public void setRegistrationExpireDate(String registrationExpireDate) {
		this.registrationExpireDate = registrationExpireDate;
	}
	
	@Column(name = "rent_price_per_day")
	public String getRentPricePerDay() {
		return rentPricePerDay;
	}

	public void setRentPricePerDay(String rentPricePerDay) {
		this.rentPricePerDay = rentPricePerDay;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

