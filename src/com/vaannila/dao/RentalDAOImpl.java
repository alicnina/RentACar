package com.vaannila.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.vaannila.domain.Rental;

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
	
	 
	 public Rental findByID(int ID){
			List<?> list = hibernateTemplate.find("from Rental where User_ID=?",ID);
			return (Rental)list.get(0);
		}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Rental> list() {
		return hibernateTemplate.find("from Rental");
	}	 

	public Rental findByKeyWords(String username, String password) {
		return null;
	}

}
