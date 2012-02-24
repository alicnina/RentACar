package etf.eminaa.managed;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.dao.UsersDAOImpl;
import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Users;

@ManagedBean
@RequestScoped
public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = 2488312033057727243L;

	protected static Logger logger = Logger.getLogger("managed");

	private String inputUsername;
	private String inputPassword;
	private boolean loginAttempted = false;

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

	private Users user;

	public String editNav() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.containsKey("userLoginBean")) {
			sessionMap.clear();
		}
		return "success";
	}

	public boolean getIsAuthorized() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.containsKey("userLoginBean")) {
			Users user = ((UserLoginBean) sessionMap.get("userLoginBean")).getUser();
			if (null != user && null != user.getAuthorities()) {
				Set<Authorities> authorities = user.getAuthorities();
				Iterator<Authorities> it = authorities.iterator();
				while (it.hasNext()) {
					Authorities auth = it.next();
					if (auth.getAuthority().equals("ROLE_USER") || auth.getAuthority().equals("ROLE_EMPLOYEE")) {
						return true;
					}
				}
			}
		}
		return false;
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

	public boolean getIsAuthorizedUser() {
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

	public String userLogin() {
		loginAttempted = true;
		if (null != inputUsername && null != inputPassword) {
			List<Users> users = usersDao.findByKeyWords("AND", "username = '" + inputUsername + "'", "password = '" + UsersDAOImpl.hashPassword(inputPassword)
					+ "'");
			user = null != users && !users.isEmpty() ? users.get(0) : null;
			if (isUserLoggedIn()) {
				// save instance of UserLoginBean to session
				FacesContext context = FacesContext.getCurrentInstance();
				ELContext elContext = context.getELContext();
				Application application = ((ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY)).getApplication();
				ValueExpression valueExpr = application.getExpressionFactory().createValueExpression(elContext, "#{userLoginBean}", UserLoginBean.class);
				valueExpr.setValue(elContext, this);
				context.getExternalContext().getSessionMap().put("userLoginBean", this);

				/*
				 * 
				 * Properties props = new Properties(); InputStream is = null;
				 * try { is = new URL("http://localhost:8080/RentACar/lang/" +
				 * user.getLanguage() + ".properties").openStream();
				 * props.load(is); } catch (MalformedURLException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); } catch
				 * (IOException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } finally { if (null != is) { try {
				 * is.close(); } catch (IOException e) { // stream is already
				 * closed } } }
				 */

				return "success";

			}
		}
		return "unsuccess";
	}

	public String getUserWelcome() {
		String msg = "User not found or invalid password!";
		if (isUserLoggedIn()) {
			msg = "User " + user.getName() + " " + user.getSurname() + " (" + user.getRole() + ") found!";
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
