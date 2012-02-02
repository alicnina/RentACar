package com.vaannila.managed;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Authorities;
import com.vaannila.domain.Rental;
import com.vaannila.domain.Users;

@ManagedBean
@RequestScoped
public class UserRegisterBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String username, password, phone, email, address, authority;
	private String name, surname, drivingLicenceNumber, idNumber;
	private Users user;
	private boolean alreadyRegistered;

	private Date idExpireDate, drivingLicenceExpireDate;

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

	@ManagedProperty(value = "#{authoritiesDao}")
	private DAOInterface<Authorities> authoritiesDao;

	@ManagedProperty(value = "#{rentalDao}")
	private DAOInterface<Rental> rentalDao;

	private boolean editable;

	public String editNav() {
		return "success";
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
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

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	private static Map<String, Object> authorityValue;

	static {
		authorityValue = new LinkedHashMap<String, Object>();
		authorityValue.put("Registered User", "ROLE_USER"); // label, value
		authorityValue.put("Employee", "ROLE_EMPLOYEE");
		authorityValue.put("Administrator", "ROLE_ADMIN");
	}

	public Map<String, Object> getAuthorityValue() {
		return authorityValue;
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
				user.setRole(authority.toString());
				user.setDrivingLicenceNumber(drivingLicenceNumber);
				user.setIdExpireDate(new java.sql.Date(idExpireDate.getTime()));
				user.setDrivingLicenceExpireDate(new java.sql.Date(drivingLicenceExpireDate.getTime()));
				usersDao.save(user);

				if (null != authority) {
					Authorities authorities = new Authorities();
					authorities.setAuthority(authority.toString());
					authorities.setUsers(user);
					authoritiesDao.save(authorities);
				}

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

	public List<Users> getUserList() {
		return usersDao.list();
	}

	public boolean isUserRegistered() {
		return null != user;
	}

	public void deleteAction(Users user) {
		usersDao.delete(user);
	}

	public void edit(Users user) {
		// TO DO: proslijditi izabranog usera na user-edit.xhtml
		String name2 = user.getName();
		// return "user-edit.xhtml";
	}

	public void userSaveEdit() {
		// TO DO: sacuvati usera kojeg smo ediiovali
	}

}
