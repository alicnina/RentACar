package etf.eminaa.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import etf.eminaa.domain.Authorities;

public class AuthoritiesDAOImpl implements DAOInterface<Authorities>, Serializable {

	private static final long serialVersionUID = 1L;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public void save(Authorities authorities) {
		hibernateTemplate.saveOrUpdate(authorities);
	}

	public void delete(Authorities authorities) {
		hibernateTemplate.delete(authorities);
	}

	public void edit(Authorities authorities) {
		hibernateTemplate.update(authorities);
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
