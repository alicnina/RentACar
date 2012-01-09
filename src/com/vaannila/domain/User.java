package com.vaannila.domain;

import java.util.Date;
import java.util.HashSet;
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
@Table(name = "User")
public class User {

	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String role;
	private String address;
	private String email;
	private String phone;
	private String IDNumber;
	private Date IDExpireDate;
	private String drivingLicenceNumber;
	private Date drivingLicenceExpireDate;
	private Boolean mailingList;
	private Set<Rental> rental = new HashSet<>(0);
	private Set<Rental> rentedRecords;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Rental> getRentalRecords() {
		return this.rental;
	}

	public void setRentalRecords(Set<Rental> rentalRecords) {
		this.rental = rentalRecords;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeUser")
	public Set<Rental> getRentedRecords() {
		return this.rentedRecords;
	}

	public void setRentedRecords(Set<Rental> rentalRecords) {
		this.rentedRecords = rentalRecords;
	}

	@Column(name = "User_Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "User_Surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "User_Username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "User_Password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "User_Roles")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "User_Address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "User_Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "User_Phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "User_ID_Number")
	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	@Column(name = "User_ID_Expire_Date")
	public Date getIDExpireDate() {
		return IDExpireDate;
	}

	public void setIDExpireDate(Date IDExpireDate) {
		this.IDExpireDate = IDExpireDate;
	}
	
	@Column(name = "User_Driving_Licence_Number")
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
	@Column(name = "User_DrivingLicence_Expire_Date")
	public Date getDrivingLicenceExpireDate() {
		return drivingLicenceExpireDate;
	}

	public void setDrivingLicenceExpireDate(Date drivingLicenceExpireDate) {
		this.drivingLicenceExpireDate = drivingLicenceExpireDate;
	}
	
	@Column(name = "User_Mailing_List")
	public Boolean getMailingList() {
		return mailingList;
	}

	public void setMailingList(Boolean mailingList) {
		this.mailingList = mailingList;
	}

}
