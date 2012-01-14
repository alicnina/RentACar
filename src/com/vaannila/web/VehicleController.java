package com.vaannila.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vaannila.dao.DAOInterface;
import com.vaannila.domain.Vehicle;

public class VehicleController extends MultiActionController {

	private DAOInterface<Vehicle> vehicleDAO;

	public void setVehicleDAO(DAOInterface<Vehicle> vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) throws Exception {
		vehicleDAO.save(vehicle);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView getEdit(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");
		modelMap.addAttribute("vehicle", vehicleDAO.findByPrimaryKey(vehicle.getId()));
		return new ModelAndView("editVehiclePage", modelMap);
	}

	public ModelAndView getDelete(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) throws Exception {
		vehicleDAO.delete(vehicle);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView saveEdit(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) throws Exception {
		logger.debug("Received request to update vehicle");
		vehicleDAO.edit(vehicle);
		return new ModelAndView("redirect:list.htm");
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("vehicleList", vehicleDAO.list());
		modelMap.addAttribute("vehicle", new Vehicle());
		return new ModelAndView("vehicleForm", modelMap);
	}
}
