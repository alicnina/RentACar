package etf.eminaa.rest;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Location;
import etf.eminaa.domain.Rental;

@ManagedBean(name = "locationService")
@SessionScoped
public class LocationService {
	private double latitude;

	private double longitude;
	private int vehicleId;

	private String pozicijaString = "";
	private MapModel model = new DefaultMapModel();
	private String lastKnownVehicleLocationResult;

	@ManagedProperty(value = "#{locationDAO}")
	private DAOInterface<Location> locationDAO;

	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDAO;

	public void getLastKnownLocation() {

		Rental currentRental = getCurrentRental(vehicleId);
		if (null != currentRental) {
			List<Location> locations = locationDAO.findByKeyWords("AND", "rental_id = " + currentRental.getId());

			if (null != locations && !locations.isEmpty()) {
				Location location = null;

				for (Location l : locations) {
					if (null == location || isOlder(location, l)) {
						location = l;
					}
				}

				latitude = location.getLattitude();
				longitude = location.getLongitude();
				pozicijaString = latitude + ", " + longitude;
			} else {
				// TODO: handle this case
			}
		} else {
			// TODO: handle this case
		}
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

	private boolean isOlder(Location firstLocation, Location secondLocation) {
		Calendar first = Calendar.getInstance();
		first.setTime(firstLocation.getStartDate());
		Calendar second = Calendar.getInstance();
		second.setTime(secondLocation.getStartDate());
		return first.before(second);
	}

	public void dodajMarker() {
		LatLng ll = new LatLng(latitude, longitude);
		model.getMarkers().add(new Marker(ll));
	}

	public String setLastKnownVehicleLocationResult() {
		return lastKnownVehicleLocationResult;
	}

	public String getLastKnownVehicleLocationResult() {
		return lastKnownVehicleLocationResult;
	}

	public void setMap() {
		pozicijaString = latitude + ", " + longitude;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}

	public DAOInterface<Location> getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(DAOInterface<Location> locationDAO) {
		this.locationDAO = locationDAO;
	}

	public DAOInterface<Rental> getRentalDAO() {
		return rentalDAO;
	}

	public void setRentalDAO(DAOInterface<Rental> rentalDAO) {
		this.rentalDAO = rentalDAO;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPozicijaString() {
		return pozicijaString;
	}

	public void setPozicijaString(String pozicijaString) {
		this.pozicijaString = pozicijaString;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
