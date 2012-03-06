package etf.eminaa.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import etf.eminaa.domain.Location;

public class LocationDAOImpl implements DAOInterface<Location>, Serializable {

	private static final long serialVersionUID = -1295689842808386948L;

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void save(Location location) {
		hibernateTemplate.saveOrUpdate(location);
	}

	@Override
	public List<Location> list() {
		// do nothing
		return null;
	}

	@Override
	public List<Location> list(String criteria) {
		// do nothing
		return null;
	}

	@Override
	public void edit(Location location) {
		// do nothing

	}

	@Override
	public void delete(Location location) {
		// do nothing

	}

	@Override
	public Location findByPrimaryKey(Object key) {
		// do nothing
		return null;
	}

	@Override
	public List<Location> findByKeyWords(String operator, String... args) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Location where ");
		for(int i = 0; i < args.length; i++){
			sb.append(args[i]);
			if(i+1 < args.length){
				sb.append(" " + operator + " ");
			}
		}
//		return hibernateTemplate.find("from Users where username=? AND password=?", username, hashPassword(password));
		return hibernateTemplate.find(sb.toString());
	}

}
