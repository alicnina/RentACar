package com.vaannila.managed;

import java.io.Serializable;

import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Users;

public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String inputUsername;
	private String inputPassword;
	private boolean loginAttempted = false;

	private DAOInterface<Users> usersDao;
	private Users user;

	public void userLogin(AjaxBehaviorEvent event) {
		loginAttempted = true;
		if (null != inputUsername && null != inputPassword) {
			user = usersDao.findByKeyWords(inputUsername, inputPassword);
		}
	}

	public String getUserWelcome() {
		String msg = "User not found or invalid password!";
		if (null != user) {
			msg = "User " + user.getName() + " " + user.getSurname() + " (" + user.getUsername() + ") found!";
		} else if (!loginAttempted) {
			msg = "Please login!";
		}
		return msg;
	}

	public boolean isUserLoggedIn() {
		return null != user;
	}

	public String getInputUsername() {
		return inputUsername;
	}

	public void setInputUsername(String inputUsername) {
		this.inputUsername = inputUsername;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
