package etf.eminaa.managed;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

@ManagedBean
@RequestScoped
public class VehicleRentalBean implements Serializable {

	private static final long serialVersionUID = -2732925993048187311L;
	
	private Vehicle vehicle;
	private Rental rental;
	
	private Date startDate;
	private String numberDays;
	private String status;
	private int id;
	
	
	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;
	
	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;
	
	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;
	
	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;
	
	// getters and setters
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public DAOInterface<Vehicle> getVehicleDao() {
		return vehicleDao;
	}
	
	public void setVehicleDao(DAOInterface<Vehicle> vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	public Rental getRental() {
		return rental;
	}
	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}
	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getNumberDays() {
		return numberDays;
	}
	public void setNumberDays(String numberDays) {
		this.numberDays = numberDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public DAOInterface<Rental> getRentalDao() {
		return rentalDao;
	}
	public void setRentalDao(DAOInterface<Rental> rentalDao) {
		this.rentalDao = rentalDao;
	}
	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
	}
	
	//
	public void vehicleRentalSave(AjaxBehaviorEvent event) {
		Rental rental = new Rental();
		rental.setStartDate(new java.sql.Date(startDate.getTime()));
		rental.setNumberDays(Integer.parseInt(numberDays));
		rental.setStatus("RENTED");
		rental.setVehicle(vehicle);
		//Users user = usersDao.findByKeyWords(userLoginBean.getInputUsername(),userLoginBean.getInputPassword());
		rental.setUsers(userLoginBean.getUser());
		rentalDao.save(rental);
	}

	public String getRentalWelcome() {
		String msg = "welcome";
		if (null != rental) {
			msg = "Vehicle " + rental.getVehicle().getModel() + " " + rental.getUsers().getUsername() + " (" + rental.getNumberDays() + ") registered!";
		}
		return msg;
	}

}
