package etf.eminaa.managed;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;


import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Users;

@ManagedBean
@RequestScoped
public class UserEditBean implements Serializable {

	private static final long serialVersionUID = -4783001584826228855L;

	private Users user = new Users();

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

	@ManagedProperty(value = "#{authoritiesDAO}")
	private DAOInterface<Authorities> authoritiesDao;

	private Boolean userEdited = null;

	private String authority;

	// save edited information about users

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

	public DAOInterface<Authorities> getAuthoritiesDao() {
		return authoritiesDao;
	}

	public void setAuthoritiesDao(DAOInterface<Authorities> authoritiesDao) {
		this.authoritiesDao = authoritiesDao;
	}

	public void userEdit(AjaxBehaviorEvent event) {
		try {
			if (null != authority) {
				Authorities authorities = authoritiesDao.findByPrimaryKey(user.getUsername());
				authorities.setAuthority(authority.toString());
				authorities.setUsers(user);
				user.getAuthorities().add(authorities);
				authoritiesDao.edit(authorities);
			}
			user.setRole(authority.toString());
			usersDao.edit(user);
			userEdited = true;
		} catch (Exception e) {
			userEdited = false;
		}
	}

	public String getUserEditResult() {
		String msg = null;
		if (null == userEdited) {
			msg = "Please edit user!";
		} else if (isUserEdited()) {
			msg = "User " + user.getUsername() + " updated!";
		} else if (!isUserEdited()) {
			msg = "Error editing user!";
		}
		return msg;
	}

	public boolean isUserEdited() {
		return userEdited;
	}

	public void setUserEdited(boolean userEdited) {
		this.userEdited = userEdited;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}
}
