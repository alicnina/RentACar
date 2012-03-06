package etf.eminaa.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import etf.eminaa.domain.Location;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Vehicle;

public class VehicleDAOImpl implements DAOInterface<Vehicle>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3973282387818984888L;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void save(Vehicle vehicle) {
		hibernateTemplate.saveOrUpdate(vehicle);
	}

	@Override
	public void delete(Vehicle vehicle) {
		if (vehicle != null) {
			Iterator<Rental> itRental = vehicle.getRental().iterator();
			while (itRental.hasNext()) {
				Rental rental = itRental.next();
				Iterator<Location> loc = rental.getLocation().iterator();
				while (loc.hasNext()) {
					hibernateTemplate.delete(loc.next());
				}
				if (rental != null) {
					hibernateTemplate.delete(rental);
				}
			}

			hibernateTemplate.delete(vehicle);
		}
	}

	public void edit(Vehicle vehicle) {
		hibernateTemplate.update(vehicle);
	}

	public Vehicle findByPrimaryKey(Object key) {
		if (key instanceof Integer) {
			int id = (Integer) key;
			List<?> list = hibernateTemplate.find("from Vehicle where vehicle_id = ?", id);
			return (Vehicle) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Vehicle> list() {
		return hibernateTemplate.find("from Vehicle");
	}

	public List<Vehicle> findByKeyWords(String operator, String... args) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Vehicle where ");
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i + 1 < args.length) {
				sb.append(" " + operator + " ");
			}
		}
		return hibernateTemplate.find(sb.toString());
	}

	@Override
	public List<Vehicle> list(String criteria) {
		return null;
	}

}
