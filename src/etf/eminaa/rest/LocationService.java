package etf.eminaa.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Vehicle;

@Component
@Path("/")
public class LocationService {

	@Autowired
	private DAOInterface<Vehicle> vehicleDAO;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRentedVehicles() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><title>REST</title></head><body><table>");
		// rented vehicles
		sb.append("<tr><th>Id</th>");
		sb.append("<th>Manufacturer/Model</th>");
		sb.append("<th>[View location]</th></tr>");

		List<Vehicle> vehicles = vehicleDAO.findByKeyWords("AND", "status = 'RENTED'");

		if (null != vehicles) {
			for (Vehicle vehicle : vehicles) {
				sb.append("<tr><th>" + vehicle.getId() + "</th>");
				sb.append("<th>" + vehicle.getManufacturer() + "/" + vehicle.getModel() + "</th>");
				sb.append("<th><a href=\"" + vehicle.getId() + "\">view location</a></th></tr>");
			}
		}

		sb.append("</table></body></html>");

		return sb.toString();
	}

	public DAOInterface<Vehicle> getVehicleDAO() {
		return vehicleDAO;
	}

	public void setVehicleDAO(DAOInterface<Vehicle> vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

}
