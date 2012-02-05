package etf.eminaa.managed;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Vehicle;

@ManagedBean
@RequestScoped
public class VehicleEditBean implements Serializable {

	private static final long serialVersionUID = -4783001584826228855L;

	private Vehicle vehicle = new Vehicle();

	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;
	
	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;

	

	private Boolean vehicleEdited = null;

	private String status;

	public void vehicleEdit(AjaxBehaviorEvent event) {
		try {
//			Rental rental = rentalDao.findByPrimaryKey(vehicle.getId());
//			rental.setVehicle(vehicle);
//			rentalDao.edit(rental);
//			vehicle.getRental().add(rental);
			vehicleDao.edit(vehicle);
			vehicleEdited = true;
		} catch (Exception e) {
			vehicleEdited = false;
		}
	}

	public String getVehicleEditResult() {
		String msg = null;
		if (null == vehicleEdited) {
			msg = "Please edit vehicle!";
		} else if (isVehicleEdited()) {
			msg = "Vehilce " + vehicle.getManufacturer() + ", " + vehicle.getModel() + " updated!";
		} else if (!isVehicleEdited()) {
			msg = "Error editing vehicle!";
		}
		return msg;
	}

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

	public Boolean isVehicleEdited() {
		return vehicleEdited;
	}

	public void setVehicleEdited(Boolean vehicleEdited) {
		this.vehicleEdited = vehicleEdited;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private static Map<String, Object> statusValue;

	static {
		statusValue = new LinkedHashMap<String, Object>();
		statusValue.put("Available", "AVAILABLE"); // label, value
		statusValue.put("On Repair", "ON_REPAIR");
		statusValue.put("Not available for now ", "NOT_AVAILABLE");
		statusValue.put("Rented", "RENTED");
	}

	public Map<String, Object> getStatusValue() {
		return statusValue;
	}
	
	public DAOInterface<Rental> getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(DAOInterface<Rental> rentalDao) {
		this.rentalDao = rentalDao;
	}

}
