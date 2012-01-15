package com.vaannila.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vaannila.domain.Authorities;

public class AuthoritiesDAOImpl implements DAOInterface<Authorities> {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public void save(Authorities rental) {
		hibernateTemplate.saveOrUpdate(rental);
	}

	public void delete(Authorities rental) {
		hibernateTemplate.delete(rental);
	}

	public void edit(Authorities rental) {
		hibernateTemplate.update(rental);
	}

	public Authorities findByPrimaryKey(Object key) {
		if (key instanceof String) {
			String username = (String) key;
			List<?> find = hibernateTemplate.find("from Authorities where username = ?", username);
			return (Authorities) find.get(0);
		} else {
			return null;
		}
	}

	public List<Authorities> list() {
		return list(null);
	}

	/**
	 * criteria will be put in WHERE clause of SQL <br/>
	 * example: criteria = "username = \"myname\" AND authority = \"ROLE_USER\"
	 */
	public List<Authorities> list(String criteria) {
		return hibernateTemplate.find("from Authorities" + (null != criteria ? " WHERE " + criteria : ""));
	}

	public Authorities findByKeyWords(String Username, String Password) {
		return null;
	}

}
