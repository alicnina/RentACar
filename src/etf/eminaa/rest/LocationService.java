package etf.eminaa.rest;

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

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getVehicleLocation(@PathParam("vehicle_id") int vehicleId) {

		// TODO: get last known location for vehicle_id
		// TODO: get last rentet vehicles for user currently driving vehicle_id
		// TODO: render last known location in google maps

		return "Received HTTP GET /location/" + vehicleId + "/";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setLastKnownVehicleLocation(@PathParam("vehicle_id") int vehicleId, @FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude) {
		
		String result = "Vehicle location successfuly updated! id:" + vehicleId + "; lat:" + latitude + ";lon:" + longitude;

		Rental currentRental = rentalDAO.findByKeyWords("AND", "vehicle_id = '" + vehicleId + "'", "rental_status = 'RENTED'");
		
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
