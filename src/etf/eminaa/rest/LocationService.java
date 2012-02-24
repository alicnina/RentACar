package etf.eminaa.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Location;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

@Component
@Path("/{vehicle_id}")
public class LocationService {

	@Autowired
	private DAOInterface<Vehicle> vehicleDAO;

	@Autowired
	private DAOInterface<Rental> rentalDAO;

	@Autowired
	private DAOInterface<Users> usersDAO;

	@Autowired
	private DAOInterface<Location> locationDAO;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getVehicleLocation(@PathParam("vehicle_id") int vehicleId) {

		String result = "Sorry, no information for vehicle with id: " + vehicleId;

		Rental currentRental = getCurrentRental(vehicleId);

		if (null != currentRental) {
			StringBuilder sb = new StringBuilder();
			sb.append("<html><head><title>REST</title></head><body><table>");
			sb.append("<tr><th>Time</th>");
			sb.append("<th>Latitude</th>");
			sb.append("<th>Longitude</th></tr>");

			List<Location> locations = locationDAO.findByKeyWords("AND", "rental_id = " + currentRental.getId());

			if (null != locations && !locations.isEmpty()) {

				for (Location location : locations) {
					sb.append("<tr><td>").append(vehicleId).append("</td>");
					sb.append("<td>").append(location.getLattitude()).append("</td>");
					sb.append("<td>").append(location.getLongitude()).append("</td></tr>");
				}
			}

			sb.append("</table></body></html>");

			result = sb.toString();

		}

		return result;
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

}
