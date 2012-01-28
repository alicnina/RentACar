package com.vaannila.web;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Authorities;
import com.vaannila.domain.Users;

public class UsersController extends MultiActionController {
	
	protected static Logger logger = Logger.getLogger("controller");

	private DAOInterface<Users> usersDAO;

	public void setUsersDAO(DAOInterface<Users> userDAO) {
		this.usersDAO = userDAO;
	}
	
	private DAOInterface<Authorities> authoritiesDAO;

	public void setAuthoritiesDAO(DAOInterface<Authorities> authoritiesDAO) {
		this.authoritiesDAO = authoritiesDAO;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		usersDAO.save(users);
		Set<Authorities> authorities = users.getAuthorities();
		if (null == authorities || authorities.isEmpty()) {
			Authorities authority = new Authorities();
			authority.setAuthority("ROLE_USER");
			authority.setUsers(users);
			authoritiesDAO.save(authority);
		}
		return new ModelAndView("redirect:list.htm");
	}

	/**
	 * Retrieves the edit page
	 * 
	 * @return the name of the JSP page
	 */
	public ModelAndView getEdit(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		modelMap.addAttribute("users", usersDAO.findByPrimaryKey(users.getUsername()));
		return new ModelAndView("editUsersPage", modelMap);
	}

	public ModelAndView getDelete(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		usersDAO.delete(usersDAO.findByPrimaryKey(users.getUsername()));
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView saveEdit(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		logger.debug("Received request to update person");
		usersDAO.edit(users);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("usersList", usersDAO.list());
		modelMap.addAttribute("users", new Users());
		return new ModelAndView("usersForm", modelMap);
	}

	public ModelAndView getLogged(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		modelMap.addAttribute("users", new Users());
		return new ModelAndView("loginForm", modelMap);
	}

	public ModelAndView getRegistered(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		modelMap.addAttribute("users", new Users());
		return new ModelAndView("usersForm", modelMap);
	}

	public ModelAndView searchDB(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		ModelMap modelMap = new ModelMap();
		Users userFound = usersDAO.findByKeyWords(users.getUsername(), users.getPassword());
		if (userFound != null) {
			modelMap.addAttribute("users", userFound);
			modelMap.addAttribute("usersList", usersDAO.list());
			return new ModelAndView("usersForm", modelMap);
		} else {
			modelMap.addAttribute("users", new Users());
			modelMap.addAttribute("error", "Users not found!");
			return new ModelAndView("loginForm", modelMap);
		}
	}

}
