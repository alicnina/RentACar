package etf.eminaa.managed;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

@ManagedBean
@RequestScoped
public class VehicleRegisterBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String manufacturer, model, registrationNumber, rentPricePerDay, status;

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

	public String editRentalNav() {
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

	private static Map<String, Object> statusValue;

	static {
		statusValue = new LinkedHashMap<String, Object>();
		statusValue.put("Available", "AVAILABLE"); // label, value
		statusValue.put("On Repair", "ON_REPAIR");
		statusValue.put("Not available for now ", "NOT_AVAILABLE");
		statusValue.put("Make Reservation", "RESERVATION");
		statusValue.put("Rented", "RENTED");
	}

	public Map<String, Object> getStatusValue() {
		return statusValue;
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

	// implemented methods
	public void vehicleRegister(AjaxBehaviorEvent event) {
		vehicle = new Vehicle();
		vehicle.setManufacturer(manufacturer);
		vehicle.setModel(model);
		vehicle.setRegistrationNumber(registrationNumber);
		vehicle.setRentPricePerDay(rentPricePerDay);
		vehicle.setStatus(status);
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
		List<Vehicle> allVehicles = vehicleDao.list();
		if (isAuthorizedUser()) {
			if (allVehicles != null) {
				Iterator<Vehicle> iter = allVehicles.iterator();
				while (iter.hasNext()) {
					Vehicle v = iter.next();
					if (v.getStatus().equals("ON_REPAIR")) {
						iter.remove();
					}
					if (v.getStatus().equals("RENTED")) {
						int id = v.getId();
						Rental rent = getCurrentRental(id);
						Date date = rent.getEndDate();
						if(date.before(getTodayDate())) {
							v.setStatus("AVAILABLE");
							vehicleDao.edit(v);
						}
					} /*if (v.getStatus().equals("RESERVATION")) { 
						int id = v.getId();
						Rental rent = getCurrentRental(id);
						Date date = rent.getStartDate();
						if(date.before(rollDays(getTodayDate(), 3))) {
							v.setStatus("AVAILABLE");
							vehicleDao.edit(v);
						}*/
					}
				}
			}
		return allVehicles;
	}

	//
	private Rental getCurrentRental(int vehicleId) {

		Rental result = null;
		List<Rental> rentalsForVehicle = rentalDao.findByKeyWords("AND", "vehicle_id = '" + vehicleId + "'");

		if (null != rentalsForVehicle && !rentalsForVehicle.isEmpty()) {

			Calendar latestDate = null;

			for (Rental currentRental : rentalsForVehicle) {
				Calendar currentDate = Calendar.getInstance();
				currentDate.setTime(currentRental.getStartDate());

				if (null == latestDate || latestDate.before(currentDate)) {
					latestDate = currentDate;
					result = currentRental;
				}
			}
		} return result;
	}

	private static java.sql.Date rollDays(java.util.Date startDate, int days) {
		return rollDate(startDate, Calendar.DATE, days);
	}

	 /**
	    * Roll the java.sql.Date forward or backward.
	    * @param startDate - The start date
	    * @param period Calendar.YEAR etc
	    * @param amount - Negative to roll backwards.
	    */
	private static java.sql.Date rollDate(java.util.Date startDate, int period, int amount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(startDate);
		gc.add(period, amount);
		return new java.sql.Date(gc.getTime().getTime());
	}
	
	
	public boolean isVehicleRegistered() {
		return null != vehicle;
	}

	public void deleteAction(Vehicle vehicle) {
		if (isAuthorizedEmployee()) {
			vehicleDao.delete(vehicle);
		}
	}

	public void edit(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//
	public boolean isAuthorizedUser() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.containsKey("userLoginBean")) {
			Users user = ((UserLoginBean) sessionMap.get("userLoginBean")).getUser();
			if (null != user && null != user.getAuthorities()) {
				Set<Authorities> authorities = user.getAuthorities();
				Iterator<Authorities> it = authorities.iterator();
				while (it.hasNext()) {
					Authorities auth = it.next();
					if (auth.getAuthority().equals("ROLE_USER")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isAuthorizedEmployee() {
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

	/**
	 * Check if the vehicle is currently rented or not.
	 * 
	 * @param startDate
	 *            - The start date
	 * @param period
	 *            Calendar.YEAR etc
	 * @param amount
	 *            - Negative to roll backwards.
	 */
	// get current date
	public static Date getTodayDate() {
		return new Date(System.currentTimeMillis());
	}

}
