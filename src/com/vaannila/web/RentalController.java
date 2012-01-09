package com.vaannila.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Rental;
import com.vaannila.domain.User;
import com.vaannila.domain.Vehicle;

public class RentalController extends MultiActionController {

	private DAOInterface<Rental> rentalDAO;

	public void setRentalDAO(DAOInterface<Rental> rentalDAO) {
		this.rentalDAO = rentalDAO;
	}
	
	private DAOInterface<Vehicle> vehicleDAO;

	public void setVehicleDAO(DAOInterface<Vehicle> vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
	
	private DAOInterface<User> userDAO;

	public void setUserDAO(DAOInterface<User> userDAO) {
		this.userDAO = userDAO;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, Rental rental, Integer userId) throws Exception {
		rental.setStatus("rented");
		rentalDAO.save(rental);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("rentalList", rentalDAO.list());
		modelMap.addAttribute("rental", new Rental());
		return new ModelAndView("rentalForm", modelMap);
	}
	
	public ModelAndView getRent(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		
		// TODO: get user object from spring security (or session)
		
		
		Rental newRental = new Rental();
		
		// set fake user
		newRental.setUser(userDAO.list().get(0));
		newRental.setEmployeeUser(userDAO.list().get(0));
		
		newRental.setVehicle(vehicleDAO.findByID(vehicle.getId()));
		newRental.setStartDate(new Date(System.currentTimeMillis()).toString());
		
		// temporary save (rental process not finished yet!)
		rentalDAO.save(newRental);
		
		modelMap.addAttribute("rental", newRental);
		modelMap.addAttribute("userId", newRental.getUser().getId());
		modelMap.addAttribute("employeeId", newRental.getEmployeeUser().getId());
		modelMap.addAttribute("vehicleId", newRental.getVehicle().getId());
		return new ModelAndView("rentalForm", modelMap);
	}

}
