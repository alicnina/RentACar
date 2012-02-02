package com.vaannila.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rental")
public class Rental {

	private int id;
	private Date startDate;
	private int numberDays;
	private String status;
	private Users users;
	private Vehicle vehicle;

	@Id
	@GeneratedValue
	@Column(name = "rental_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users user) {
		this.users = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "rent_start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "rent_number_days")
	public Integer getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(Integer numberDays) {
		this.numberDays = numberDays;
	}

	@Column(name = "rent_status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
