package com.vaannila.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vaannila.domain.Vehicle;

public class VehicleDAOImpl implements DAOInterface<Vehicle> {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void save(Vehicle vehicle) {
		hibernateTemplate.saveOrUpdate(vehicle);
	}

	public void delete(Vehicle vehicle) {
		hibernateTemplate.delete(vehicle);
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

	public Vehicle findByKeyWords(String Username, String Password) {
		return null;
	}

}
