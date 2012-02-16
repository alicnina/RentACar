package etf.eminaa.rest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

@Path("/{vehicle_id}")
@ManagedBean
@RequestScoped
public class LocationService {

	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;

	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

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

		// TODO: store vehicle location into DB
		

		return result;
	}

	public DAOInterface<Vehicle> getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(DAOInterface<Vehicle> vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public DAOInterface<Rental> getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(DAOInterface<Rental> rentalDao) {
		this.rentalDao = rentalDao;
	}

	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
	}

}
