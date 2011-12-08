package com.vaannila.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.User;

public class UserController extends MultiActionController {

	private DAOInterface<User> userDAO;

	public void setUserDAO(DAOInterface<User> userDAO) {
		this.userDAO = userDAO;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		userDAO.save(user);
		return new ModelAndView("redirect:list.htm");
	}

	/**
	 * Retrieves the edit page
	 * 
	 * @return the name of the JSP page
	 */
	public ModelAndView getEdit(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		modelMap.addAttribute("user", userDAO.findByID(user.getId()));
		return new ModelAndView("editUserPage", modelMap);
	}

	public ModelAndView getDelete(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		userDAO.delete(user);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView saveEdit(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		logger.debug("Received request to update person");
		userDAO.edit(user);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("userList", userDAO.list());
		modelMap.addAttribute("user", new User());
		return new ModelAndView("userForm", modelMap);
	}

	public ModelAndView getLogged(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		modelMap.addAttribute("user", new User());
		return new ModelAndView("loginForm", modelMap);
	}

	public ModelAndView searchDB(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		ModelMap modelMap = new ModelMap();
		User userFound = userDAO.findByKeyWords(user.getUsername(), user.getPassword());
		if (userFound != null) {
			modelMap.addAttribute("user", userFound);
			modelMap.addAttribute("userList", userDAO.list());
			// TODO: save user object to session
			return new ModelAndView("userForm", modelMap);
		}
		else{
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("error", "User not found!");
			return new ModelAndView("loginForm", modelMap);
		}
	}

}
