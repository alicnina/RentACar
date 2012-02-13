package etf.eminaa.managed;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
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
		if (isAuthorizedUser()) {
			try {
				Vehicle newVehicle = vehicleDao.findByPrimaryKey(vehicle.getId());
				vehicleDao.edit(newVehicle);
				vehicleEdited = true;
			} catch (Exception e) {
				vehicleEdited = false;
			}
		}
	}

	public String getVehicleEditResult() {
		String msg = null;
		if (null == vehicleEdited) {
			msg = "Please edit vehicle!";
		} else if (isVehicleEdited()) {
			msg = "Vehicle " + vehicle.getManufacturer() + ", " + vehicle.getModel() + " updated!";
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
		statusValue.put("Rented", "RESERVATION");
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

	public boolean isAuthorizedUser() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.containsKey("userLoginBean")) {
			Users user = ((UserLoginBean) sessionMap.get("userLoginBean")).getUser();
			if (null != user && null != user.getAuthorities()) {
				Set<Authorities> authorities = user.getAuthorities();
				Iterator<Authorities> it = authorities.iterator();
				while (it.hasNext()) {
					Authorities auth = it.next();
					if (auth.getAuthority().equals("ROLE_EMPLOYEE")) {
						return true;
					}
				}
			}

		}
		return false;
	}

}
