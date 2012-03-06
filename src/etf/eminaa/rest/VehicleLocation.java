package etf.eminaa.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.FormParam;
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

@Component
@Path("/{vehicle_id}")
@ManagedBean
@SessionScoped
public class VehicleLocation {

	@Autowired
	private DAOInterface<Rental> rentalDAO;

	@Autowired
	private DAOInterface<Location> locationDAO;

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

	public DAOInterface<Rental> getRentalDAO() {
		return rentalDAO;
	}

	public void setRentalDAO(DAOInterface<Rental> rentalDAO) {
		this.rentalDAO = rentalDAO;
	}
}
