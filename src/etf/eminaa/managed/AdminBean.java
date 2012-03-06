package etf.eminaa.managed;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

@ManagedBean
@RequestScoped
public class AdminBean implements Serializable {

	private static final long serialVersionUID = -2265078997942239755L;

	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;

	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

	private Users user = new Users();

	public AdminBean() {
		UserLoginBean userLoginBean = (UserLoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoginBean");
		user = userLoginBean.getUser();
	}

	// implemented methods
	// Number of regular users (ROLE_USER)
	public int getNoUsers() {
		List<Users> allUsers = usersDao.findByKeyWords("AND", "role = 'ROLE_USER'");
		return allUsers.size();
	}

	// number of employees (ROLE_EMPLYEE)
	public int getNoEmployees() {
		List<Users> allEmployees = usersDao.findByKeyWords("AND", "role = 'ROLE_EMPLOYEE'");
		return allEmployees.size();
	}

	/*
	 * @param null;
	 * 
	 * @return Number of Vehicles Rented Today
	 */
	public int getNoRentedVehicles() {
		return countRentals(true);
		//return 0;
	}

	// Total Number of Vehicles RESERVED today
	public int getNoReservedVehicles() {
		return countRentals(false);
		//return 0;
	}

	//
	private int countRentals(boolean isRented) {
		List<Rental> allRentals = rentalDao.list();
		int numberRentings = 0;
		for (Rental rental : allRentals) {
			Date startDate = rental.getStartDate();
			Date endDate = rental.getEndDate();
			if (isRented) {
				if (rental.getVehicle().getStatus().equals("RENTED"))
					if (getTodayDate().after(startDate) && getTodayDate().before(endDate)) {
						numberRentings++;
					}
			}
			else {
				if (rental.getVehicle().getStatus().equals("RESERVATION"))
					if (getTodayDate().after(startDate) && getTodayDate().before(endDate)) {
						numberRentings++;
					}
			}
		}
		return numberRentings;
	}

	// authorization ADMIN
	public boolean getIsAuthorizedAdmin() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.containsKey("userLoginBean")) {
			Users user = ((UserLoginBean) sessionMap.get("userLoginBean")).getUser();
			if (null != user && null != user.getAuthorities()) {
				Set<Authorities> authorities = user.getAuthorities();
				Iterator<Authorities> it = authorities.iterator();
				while (it.hasNext()) {
					Authorities auth = it.next();
					if (auth.getAuthority().equals("ROLE_ADMIN")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	//
	public static Date getTodayDate() {
		return new Date(System.currentTimeMillis());
	}

	// getters and setters
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
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

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

}
