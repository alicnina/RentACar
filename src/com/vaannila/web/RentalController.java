package com.vaannila.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Rental;
import com.vaannila.domain.Users;
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

	private DAOInterface<Users> usersDAO;

	public void setUsersDAO(DAOInterface<Users> usersDAO) {
		this.usersDAO = usersDAO;
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
		newRental.setUsers(usersDAO.list().get(0));

		newRental.setVehicle(vehicleDAO.findByPrimaryKey(vehicle.getId()));
		newRental.setStartDate(new Date(System.currentTimeMillis()).toString());

		// temporary save (rental process not finished yet!)
		rentalDAO.save(newRental);

		modelMap.addAttribute("rental", newRental);
		modelMap.addAttribute("username", newRental.getUsers().getUsername());
		modelMap.addAttribute("vehicleId", newRental.getVehicle().getId());
		return new ModelAndView("rentalForm", modelMap);
	}

}
