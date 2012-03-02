package etf.eminaa.rest;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Location;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
@Path("/{vehicle_id}")
@ManagedBean
@SessionScoped
public class VehicleLocation {

	@Autowired
	private DAOInterface<Vehicle> vehicleDAO;

	@Autowired
	private DAOInterface<Rental> rentalDAO;

	@Autowired
	private DAOInterface<Users> usersDAO;

	@Autowired
	private DAOInterface<Location> locationDAO;

	@Context
	private ServletContext servletContext;

	private MapModel model = new DefaultMapModel();

	private String latLonPositionString;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getVehicleLocation(@PathParam("vehicle_id") int vehicleId) {

		// TODO FIX DUMMY IMPLEMENTATION!
		List<Rental> findByKeyWords = rentalDAO.findByKeyWords("AND", "vehicle_id = " + vehicleId);
		List<Location> locations = locationDAO.findByKeyWords("AND", "rental_id = " + findByKeyWords.get(0));
		Location location = locations.get(0);

		latLonPositionString = location.getLattitude() + ", " + location.getLongitude();

		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(servletContext, "WEB-INF/templates");

		Map<String, Object> props = new HashMap<String, Object>();
		props.put("latLonPositionString", "41.381542, 21.122893");
		props.put("mapModel", model);

		try {
			Template tpl = cfg.getTemplate("vehicleLocation.ftl");
			return FreeMarkerTemplateUtils.processTemplateIntoString(tpl, props);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (TemplateException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
		}

		return null;

		// String result = "Sorry, no information for vehicle with id: " +
		// vehicleId;
		//
		// Rental currentRental = getCurrentRental(vehicleId);
		//
		// if (null != currentRental) {
		// StringBuilder sb = new StringBuilder();
		// sb.append("<html><head><title>REST</title></head><body><table>");
		// // vehicle coordinates
		// sb.append("<tr><th>Time</th>");
		// sb.append("<th>Latitude</th>");
		// sb.append("<th>Longitude</th></tr>");
		//
		// List<Location> locations = locationDAO.findByKeyWords("AND",
		// "rental_id = " + currentRental.getId());
		//
		// if (null != locations && !locations.isEmpty()) {
		//
		// for (Location location : locations) {
		// // location values
		// sb.append("<tr><td>").append(vehicleId).append("</td>");
		// sb.append("<td>").append(location.getLattitude()).append("</td>");
		// sb.append("<td>").append(location.getLongitude()).append("</td></tr>");
		//
		// }
		// }
		// List<Users> users = usersDAO.findByKeyWords("AND", "username = '" +
		// currentRental.getUsers().getUsername() + "'");
		//
		// // information about person renting a car
		// sb.append("<tr><th>Name of Person Currently renting Vehicle</th>");
		// sb.append("<th>Surname </th>");
		// sb.append("<th>Username of Person Currently renting Vehicle</th></tr>");
		//
		// if (null != users && !users.isEmpty()) {
		//
		// for (Users user : users) {
		// // location values
		// sb.append("<tr><td>").append(user.getName()).append("</td>");
		// sb.append("<td>").append(user.getSurname()).append("</td>");
		// sb.append("<td>").append(user.getUsername()).append("</td></tr>");
		//
		// }
		// }
		//
		// List<Rental> allUserRentals = rentalDAO.findByKeyWords("AND",
		// "username = '" + currentRental.getUsers().getUsername() + "'");
		// // information about vehicle rented by the same person
		// sb.append("<tr><th>List of the Vehicles (Manufacturer)</th>");
		// sb.append("<th> List of the Vehicles (Model) </th>");
		// sb.append("<th> Total Number </th></tr>");
		//
		// if (null != allUserRentals && !allUserRentals.isEmpty()) {
		// //sb.append("<tr><td>").append(allUserRentals.size()).append("</td>");
		// int noRentals = 0;
		// for (Rental rental : allUserRentals) {
		// // location values
		// noRentals++;
		// sb.append("<tr><td>").append(rental.getVehicle().getManufacturer()).append("</td>");
		// sb.append("<td>").append(rental.getVehicle().getModel()).append("</td>");
		//
		// }
		// sb.append("<td>").append(noRentals).append("</td></tr>");
		// }
		//
		// sb.append("</table></body></html>");
		//
		// result = sb.toString();
		//
		// }
		//
		// return result;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setLastKnownVehicleLocation(@PathParam("vehicle_id") int vehicleId, @FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude) {

		String result = "Vehicle location successfuly updated! id:" + vehicleId + "; lat:" + latitude + ";lon:" + longitude;

		Location location = new Location();
		try {

			Rental currentRental = getCurrentRental(vehicleId);

			if (null != currentRental) {

				location.setRental(currentRental);
				location.setLattitude(latitude);
				location.setLongitude(longitude);
				location.setStartDate(new Date(System.currentTimeMillis()));

				locationDAO.save(location);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			result = "Error updating location (" + location.toString() + ") for vehicleId " + vehicleId;
		}

		return result;
	}

	private Rental getCurrentRental(int vehicleId) {

		Rental result = null;
		List<Rental> rentalsForVehicle = rentalDAO.findByKeyWords("AND", "vehicle_id = '" + vehicleId + "'");

		if (null != rentalsForVehicle && !rentalsForVehicle.isEmpty()) {

			Calendar latestDate = null;

			for (Rental currentRental : rentalsForVehicle) {
				Calendar currentDate = Calendar.getInstance();
				currentDate.setTime(currentRental.getStartDate());

				if (null == latestDate || latestDate.before(currentDate)) {
					latestDate = currentDate;
					result = currentRental;
				}
			}
		}

		return result;
	}

	public DAOInterface<Vehicle> getVehicleDAO() {
		return vehicleDAO;
	}

	public void setVehicleDAO(DAOInterface<Vehicle> vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	public DAOInterface<Rental> getRentalDAO() {
		return rentalDAO;
	}

	public void setRentalDAO(DAOInterface<Rental> rentalDAO) {
		this.rentalDAO = rentalDAO;
	}

	public DAOInterface<Users> getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(DAOInterface<Users> usersDAO) {
		this.usersDAO = usersDAO;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public String getLatLonPositionString() {
		return latLonPositionString;
	}

	public void setLatLonPositionString(String latLonPositionString) {
		this.latLonPositionString = latLonPositionString;
	}
}
