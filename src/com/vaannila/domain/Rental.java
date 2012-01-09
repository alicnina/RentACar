package com.vaannila.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Rental")
public class Rental {

	private int id;
	private String startDate;
	private String numberOfDays;
	private String status;
	private User user;
	private User employeeUser;
	private Vehicle vehicle;

	@Id
	@GeneratedValue
	@Column(name = "Rental_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Employee_User_ID", nullable = false)
	public User getEmployeeUser() {
		return this.employeeUser;
	}

	public void setEmployeeUser(User user) {
		this.employeeUser = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Vehicle_ID", nullable = false)
	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "Rent_Start_Date")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "Rent_Number_Of_Days")
	public String getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	@Column(name = "Rent_Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
