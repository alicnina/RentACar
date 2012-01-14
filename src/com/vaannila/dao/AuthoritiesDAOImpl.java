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

	@Override
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
			List<?> list = hibernateTemplate.find("from Authorities where username = ?", username);
			return (Authorities) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Authorities> list() {
		return hibernateTemplate.find("from Authorities");
	}

	public Authorities findByKeyWords(String username, String password) {
		return null;
	}

}
