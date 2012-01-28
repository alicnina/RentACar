package com.vaannila.managed;

import java.io.Serializable;

import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Authorities;
import com.vaannila.domain.Users;

public class UserRegisterBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String username, password, phone, email, authority;

	private DAOInterface<Users> usersDao;
	private DAOInterface<Authorities> authoritiesDAO;

	private Users user;

	public void userRegister(AjaxBehaviorEvent event) {
		if (null != username && null != password) {
			user = new Users();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setPhone(phone);
			usersDao.save(user);
			
			if(null != authority){
				Authorities authorities = new Authorities();
				authorities.setAuthority(authority);
				authorities.setUsers(user);
				authoritiesDAO.save(authorities);
			}
			else{
				// TODO: add validation!
			}
		}
		else {
			// TODO: add validation!
		}
	}

	public String getUserWelcome() {
		String msg = "Please register!";
		if (null != user) {
			msg = "User " + user.getName() + " " + user.getSurname() + " (" + user.getUsername() + ") registered!";
		}
		return msg;
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
	}

	public DAOInterface<Authorities> getAuthoritiesDAO() {
		return authoritiesDAO;
	}

	public void setAuthoritiesDAO(DAOInterface<Authorities> authoritiesDAO) {
		this.authoritiesDAO = authoritiesDAO;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
