package com.vaannila.managed;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Authorities;
import com.vaannila.domain.Users;
import com.vaannila.domain.Vehicle;

@ManagedBean
@RequestScoped
public class VehicleEditBean implements Serializable {

	private static final long serialVersionUID = -4783001584826228855L;

	private Vehicle vehicle;
	
	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;

	// getters and setters
	private static Map<String, Object> statusValue;

	static {
		statusValue = new LinkedHashMap<String, Object>();
		statusValue.put("Rented", "RENTED"); // label, value
		statusValue.put("On Service", "ON_SERVICE");
		statusValue.put("Available", "AVAILABLE");
	}

	public Map<String, Object> getStatusValue() {
		return statusValue;
	}

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


}
