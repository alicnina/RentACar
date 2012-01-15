package com.vaannila.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.vaannila.domain.Users;

public class UsersDAOImpl implements DAOInterface<Users> {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public void save(Users user) {
		String passToHash = user.getPassword();
		user.setPassword(hashPassword(passToHash));
		hibernateTemplate.saveOrUpdate(user);
	}

	public void delete(Users user) {
		hibernateTemplate.delete(user);
	}

	public void edit(Users user) {
		String passToHash = user.getPassword();
		user.setPassword(hashPassword(passToHash));
		hibernateTemplate.update(user);
	}

	@SuppressWarnings("unchecked")
	public List<Users> list() {
		return hibernateTemplate.find("from Users");
	}

	public Users findByPrimaryKey(Object key) {
		if (key instanceof String) {
			String username = (String) key;
			List<?> list = hibernateTemplate.find("from Users where username=?", username);
			return (Users) list.get(0);
		} else {
			return null;
		}
	}

	public Users findByKeyWords(String username, String password) {
		List<?> list = hibernateTemplate.find("from Users where username=? AND password=?", username, hashPassword(password));
		if (list.isEmpty()) {
			return null;
		} else {
			return (Users) list.get(0);
		}
	}

	// code to find users while logging from spring table Users
	public Users findByKeyWordsFromUsers(String username, String password) {
		List<?> list = hibernateTemplate.find("from Users where username=? AND password=?", username, hashPassword(password));
		if (list.isEmpty()) {
			return null;
		} else {
			return (Users) list.get(0);
		}
	}

	private String hashPassword(String password) {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);

		} catch (NoSuchAlgorithmException nsae) {

		}
		return hashword;
	}

	@Override
	public List<Users> list(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
