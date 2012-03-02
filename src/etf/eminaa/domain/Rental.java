package etf.eminaa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rental")
public class Rental {

	private int id;
	private Date startDate;
	private Date endDate;
	private int numberDays;
	private Users users;
	
	private Vehicle vehicle;
	
	private Set<Location> location = new HashSet<Location>(0);

	@Id
	@GeneratedValue
	@Column(name = "rental_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users user) {
		this.users = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
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
	
	@Column(name = "rent_end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "rent_number_days")
	public int getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(int numberDays) {
		this.numberDays = numberDays;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "rental")
	public Set<Location> getLocation() {
		return this.location;
	}

	public void setLocation(Set<Location> rentalLocation) {
		this.location = rentalLocation;
	}

}
