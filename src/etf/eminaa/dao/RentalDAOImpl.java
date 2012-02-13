package etf.eminaa.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import etf.eminaa.domain.Rental;

public class RentalDAOImpl implements DAOInterface<Rental> {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void save(Rental rental) {
		hibernateTemplate.saveOrUpdate(rental);
	}

	public void delete(Rental rental) {
		hibernateTemplate.delete(rental);
	}

	public void edit(Rental rental) {
		hibernateTemplate.update(rental);
	}

	public Rental findByPrimaryKey(Object key) {
		if (key instanceof Integer) {
			int id = (Integer) key;
			List<?> list = hibernateTemplate.find("from rental where rental_id=?", id);
			return (Rental) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rental> list() {
		return hibernateTemplate.find("from Rental");
	}

	public Rental findByKeyWords(String username, String password) {
		return null;
	}

	@Override
	public List<Rental> list(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
