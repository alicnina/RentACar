package com.vaannila.web;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Authorities;
import com.vaannila.domain.Users;

public class AuthoritiesController extends MultiActionController {

	private DAOInterface<Authorities> authoritiesDAO;

	public void setAuthoritiesDAO(DAOInterface<Authorities> authoritiesDAO) {
		this.authoritiesDAO = authoritiesDAO;
	}
	
	private DAOInterface<Users> usersDAO;

	public void setUsersDAO(DAOInterface<Users> usersDAO) {
		this.usersDAO = usersDAO;
	}

	public void add(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		Authorities loadedAuthority = authoritiesDAO.findByPrimaryKey(users.getUsername());
		Set<Authorities> authorities = users.getAuthorities();
		if (null != authorities && !authorities.isEmpty()) {
			loadedAuthority.setAuthority(authorities.iterator().next().getAuthority());
		}
		authoritiesDAO.save(loadedAuthority);
	}

	public ModelAndView getRole(HttpServletRequest request, HttpServletResponse response, Users users) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		Users loadedUsers = usersDAO.findByPrimaryKey(users.getUsername());
		modelMap.addAttribute("users", loadedUsers);
		return new ModelAndView("addRolePage", modelMap);
	}

}
