package etf.eminaa.managed;

import java.io.Serializable;
import java.util.Date;
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

@ManagedBean
@RequestScoped
public class UserRegisterBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String username, password, phone, email, address, authority;
	
	private String name, surname, drivingLicenceNumber, idNumber, language;

	private Users user;
	private boolean alreadyRegistered;

	private Date idExpireDate, drivingLicenceExpireDate;

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

	@ManagedProperty(value = "#{authoritiesDAO}")
	private DAOInterface<Authorities> authoritiesDao;

	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;

	public String editNav() {
		return "success";
	}

	public String editRentalNav() {
		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getIdExpireDate() {
		return idExpireDate;
	}

	public void setIdExpireDate(Date idExpireDate) {
		this.idExpireDate = idExpireDate;
	}

	public Date getDrivingLicenceExpireDate() {
		return drivingLicenceExpireDate;
	}

	public void setDrivingLicenceExpireDate(Date drivingLicenceExpireDate) {
		this.drivingLicenceExpireDate = drivingLicenceExpireDate;
	}

	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public DAOInterface<Authorities> getAuthoritiesDao() {
		return authoritiesDao;
	}

	public void setAuthoritiesDao(DAOInterface<Authorities> authoritiesDao) {
		this.authoritiesDao = authoritiesDao;
	}

	public DAOInterface<Rental> getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(DAOInterface<Rental> rentalDao) {
		this.rentalDao = rentalDao;
	}

	public boolean isAlreadyRegistered() {
		return alreadyRegistered;
	}

	public void setAlreadyRegistered(boolean alreadyRegistered) {
		this.alreadyRegistered = alreadyRegistered;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	private static Map<String, Object> authorityValue;

	static {
		authorityValue = new LinkedHashMap<String, Object>();
		authorityValue.put("Registered User", "ROLE_USER"); // label, value
		authorityValue.put("Employee", "ROLE_EMPLOYEE");
		authorityValue.put("Administrator", "ROLE_ADMIN");
		authorityValue.put("Restricted", "ROLE_RESTRICTED");
	}

	public Map<String, Object> getAuthorityValue() {
		return authorityValue;
	}

	
	private static Map<String, Object> languageValue;

	static {
		languageValue = new LinkedHashMap<String, Object>();
		languageValue.put("English", "en"); // label, value
		languageValue.put("Bosnian", "ba");
	}

	public Map<String, Object> getLanguageValue() {
		return languageValue;
	}

	// implemented methods
	public void userRegister(AjaxBehaviorEvent event) {
		if (null != username && null != password) {
			user = usersDao.findByKeyWords(username, password);
			if (null == user) {
				user = new Users();
				user.setUsername(username);
				user.setPassword(password);
				user.setName(name);
				user.setSurname(surname);
				user.setAddress(address);
				user.setEmail(email);
				user.setPhone(phone);
				user.setIdNumber(idNumber);
				user.setRole("ROLE_USER");
				user.setDrivingLicenceNumber(drivingLicenceNumber);
				user.setIdExpireDate(new java.sql.Date(idExpireDate.getTime()));
				user.setDrivingLicenceExpireDate(new java.sql.Date(drivingLicenceExpireDate.getTime()));
				user.setLanguage(language);
				usersDao.save(user);
				Authorities authorities = new Authorities();
				authorities.setAuthority("ROLE_USER");
				authorities.setUsers(user);
				authoritiesDao.save(authorities);

			} else {
				alreadyRegistered = true;
			}
		} else {
			// TODO: add validation!
		}
	}

	public String getUserWelcome() {
		String msg = "Please register!";
		if (null != user && alreadyRegistered == false) {
			msg = "User " + user.getName() + " " + user.getSurname() + " (" + user.getUsername() + ") registered! Please login";
		} else if (null != user && alreadyRegistered == true) {
			msg = "User " + user.getName() + " " + user.getSurname() + " (" + user.getUsername() + ") is already registered!";
		}
		return msg;
	}

	public List<Users> getUserList() throws Exception {
		if (getIsAuthorizedUser()) {
			return usersDao.list();
		} else {
			throw new Exception();
		}
	}

	public boolean isUserRegistered() {
		return null != user;
	}

	public void deleteAction(Users user) throws Exception {
		if (getIsAuthorizedUser()) {
			usersDao.delete(user);
		} else {
			throw new Exception("User is not authorized for this call!");
		}
	}

	public boolean getIsAuthorizedUser() {
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

}
