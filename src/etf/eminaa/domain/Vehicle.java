package etf.eminaa.domain;

import java.util.Date;
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
	private Date productionDate;
	private String registrationNumber;
	private Date registrationExpireDate;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicle")
	public Set<Rental> getRental() {
		return this.rental;
	}

	public void setRental(Set<Rental> rentalRecords) {
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
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
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
	public Date getRegistrationExpireDate() {
		return registrationExpireDate;
	}

	public void setRegistrationExpireDate(Date registrationExpireDate) {
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

