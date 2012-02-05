package etf.eminaa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class Users {
	
	private String username;
	private String password;
	private boolean enabled;
	private String name;
	private String surname;
	private String address;
	private String email;
	private String phone;
	private String idNumber;
	private String drivingLicenceNumber;
	private String role;

	private Date idExpireDate, drivingLicenceExpireDate;
	
	private Boolean mailingList;
	private Set<Rental> rental = new HashSet<Rental>(0);

	private Set<Authorities> authorities = new HashSet<Authorities>(0);
	
		
	@Id
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Rental> getRental() {
		return this.rental;
	}

	public void setRental(Set<Rental> rentalRecords) {
		this.rental = rentalRecords;
	}

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Authorities> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "id_number")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Column(name = "id_expire_date")
	public Date getIdExpireDate() {
		return idExpireDate;
	}

	public void setIdExpireDate(Date idExpireDate) {
		this.idExpireDate = idExpireDate;
	}
	
	@Column(name = "driving_licence_number")
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
	@Column(name = "driving_licence_expire_date")
	public Date getDrivingLicenceExpireDate() {
		return drivingLicenceExpireDate;
	}

	public void setDrivingLicenceExpireDate(Date drivingLicenceExpireDate) {
		this.drivingLicenceExpireDate = drivingLicenceExpireDate;
	}
	
	@Column(name = "mailing_list")
	public Boolean getMailingList() {
		return mailingList;
	}

	public void setMailingList(Boolean mailingList) {
		this.mailingList = mailingList;
	}

	@Column(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
