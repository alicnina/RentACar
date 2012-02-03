package com.vaannila.managed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.vaannila.dao.DAOInterface;

import com.vaannila.domain.Rental;
import com.vaannila.domain.Users;
import com.vaannila.domain.Vehicle;

@ManagedBean
@RequestScoped
public class VehicleRegisterBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String manufacturer, model, registrationNumber, rentPricePerDay;

	private boolean alreadyRegistered = false;

	private Vehicle vehicle;

	private Date productionDate, registrationExpireDate;

	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;
	
	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;
	
	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;
	
	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;
	
	public String editNav() {
		return "success";
	}
	
	// getters and setters
	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
	}
	
	public DAOInterface<Rental> getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(DAOInterface<Rental> rentalDao) {
		this.rentalDao = rentalDao;
	}

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRentPricePerDay() {
		return rentPricePerDay;
	}

	public void setRentPricePerDay(String rentPricePerDay) {
		this.rentPricePerDay = rentPricePerDay;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getRegistrationExpireDate() {
		return registrationExpireDate;
	}

	public void setRegistrationExpireDate(Date registrationExpireDate) {
		this.registrationExpireDate = registrationExpireDate;
	}

	public DAOInterface<Vehicle> getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(DAOInterface<Vehicle> vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public boolean isAlreadyRegistered() {
		return alreadyRegistered;
	}

	public void setAlreadyRegistered(boolean alreadyRegistered) {
		this.alreadyRegistered = alreadyRegistered;
	}

	public void vehicleRegister(AjaxBehaviorEvent event) {
		vehicle = new Vehicle();
		vehicle.setManufacturer(manufacturer);
		vehicle.setModel(model);
		vehicle.setRegistrationNumber(registrationNumber);
		vehicle.setRentPricePerDay(rentPricePerDay);
		vehicle.setProductionDate(new java.sql.Date(productionDate.getTime()));
		vehicle.setRegistrationExpireDate(new java.sql.Date(registrationExpireDate.getTime()));
		vehicleDao.save(vehicle);
	}

	public String getVehicleWelcome() {
		String msg = "welcome";
		if (null != vehicle) {
			msg = "Vehicle " + vehicle.getManufacturer() + " " + vehicle.getModel() + " (" + vehicle.getProductionDate() + ") registered!";
		}
		return msg;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleDao.list();
	}

	public boolean isVehicleRegistered() {
		return null != vehicle;
	}
	
	public void deleteAction(Vehicle vehicle) {
		vehicleDao.delete(vehicle);
	}
	
	
	public void edit(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void save() {
        Users users = new Users(); 
        boolean editable = false;
    }
}
