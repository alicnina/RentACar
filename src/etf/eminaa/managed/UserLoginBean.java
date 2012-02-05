package etf.eminaa.managed;

import java.io.Serializable;

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

	public void userLogin(AjaxBehaviorEvent event) {
		loginAttempted = true;
		if (null != inputUsername && null != inputPassword) {
			user = usersDao.findByKeyWords(inputUsername, inputPassword);
			if (isUserLoggedIn()) {
				// save instance of UserLoginBean to session
				FacesContext context = FacesContext.getCurrentInstance();
				ELContext elContext = context.getELContext();
				Application application = ((ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY)).getApplication();
				ValueExpression valueExpr = application.getExpressionFactory().createValueExpression(elContext, "#{userLoginBean}", UserLoginBean.class);
				valueExpr.setValue(elContext, this);
				context.getExternalContext().getSessionMap().put("userLoginBean", this);
			}
		}
	}

	public String getUserWelcome() {
		String msg = "User not found or invalid password!";
		if (isUserLoggedIn()) {
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
