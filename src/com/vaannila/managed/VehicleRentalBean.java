package com.vaannila.managed;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Rental;
import com.vaannila.domain.Users;
import com.vaannila.domain.Vehicle;

@ViewScoped
public class VehicleRentalBean implements Serializable {

	private static final long serialVersionUID = -4496108825197569738L;
	private Date startDate;
	private String numberDays;
	private String status;
	private int vehicleId;
	private Rental rental;
	
	private DAOInterface<Vehicle> vehicleDao;
	private DAOInterface<Rental> rentalDao;
	private DAOInterface<Users> usersDao;
	
	private UserLoginBean userLoginBean;
		
	// getters and setters
	public Rental getRental() {
		return rental;
	}
	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
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
	
	public DAOInterface<Vehicle> getVehicleDao() {
		return vehicleDao;
	}
	public void setVehicleDao(DAOInterface<Vehicle> vehicleDao) {
		this.vehicleDao = vehicleDao;
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
	public void vehicleRental(AjaxBehaviorEvent event) {
		Rental rental = new Rental();
		rental.setStartDate(new java.sql.Date(startDate.getTime()));
		rental.setNumberDays(Integer.parseInt(numberDays));
		rental.setStatus("RENTED");
		Vehicle vehicle = vehicleDao.findByPrimaryKey(vehicleId);
		rental.setVehicle(vehicle);
		Users user = usersDao.findByKeyWords(userLoginBean.getInputUsername(),userLoginBean.getInputPassword());
		rental.setUsers(user);
		rentalDao.save(rental);
			
		/*Rental rental = new Rental();
		rental.setVehicle(vehicle);
		Users user = usersDao.findByKeyWords(userLoginBean.getInputUsername(), userLoginBean.getInputPassword());
		rental.setUsers(user);
		rentalDao.save(rental);*/
	}

	public String getVehicleWelcome() {
		// String msg = "You are user: " + userLoginBean.getUser().getName() +
		// " (Role: " +
		// userLoginBean.getUser().getAuthorities().iterator().next() +
		// ")! Please add new vehicle!";
		String msg = "welcome";
		if (null != rental) {
			msg = "Vehicle " + rental.getVehicle().getModel() + " " + rental.getUsers().getUsername() + " (" + rental.getNumberDays() + ") registered!";
		}
		return msg;
	}
	

}
