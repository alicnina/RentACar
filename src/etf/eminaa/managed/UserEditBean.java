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

	private String authority, language;

	
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
		languageValue.put("English", "eng"); // label, value
		languageValue.put("Bosnian", "bos");
		languageValue.put("German", "ger");
	}

	public Map<String, Object> getLanguageValue() {
		return languageValue;
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
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	// implemented methods
	public void userEdit(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin()) {
			if (null != authority) {
				Authorities authorities = authoritiesDao.findByPrimaryKey(user.getUsername());
				authorities.setAuthority(authority.toString());
				authorities.setUsers(user);
				user.getAuthorities().add(authorities);
				authoritiesDao.edit(authorities);
			}
			user.setRole(authority.toString());
			user.setLanguage(language);
			usersDao.edit(user);
			userEdited = true;
		} else {
			throw new Exception("User is not authorized for this call!");
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
}
