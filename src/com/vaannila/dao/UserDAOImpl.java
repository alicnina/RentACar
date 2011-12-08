package com.vaannila.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.vaannila.domain.User;

public class UserDAOImpl implements DAOInterface<User> {

	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void save(User user) {
		// TODO: hash password
		String passToHash = user.getPassword();
		user.setPassword(hashPassword(passToHash));
		hibernateTemplate.saveOrUpdate(user);
	}
	
	public void delete(User user) {
		hibernateTemplate.delete(user);
	}
	
	public void edit(User user ) {
		String passToHash = user.getPassword();
		user.setPassword(hashPassword(passToHash));
		// TODO: has pasword
		 hibernateTemplate.update(user);
	 }
	
	 
	 public User findByID(int ID){
			List<?> list = hibernateTemplate.find("from User where User_ID=?",ID);
			return (User)list.get(0);
		}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> list() {
		return hibernateTemplate.find("from User");
	}	 

	public User findByKeyWords(String username, String password) {
		// TODO: hash paswords
		List<?> list = hibernateTemplate.find("from User where User_Username=? AND User_Password=?", username, hashPassword(password));
		if(list.isEmpty()){
			return null;
		}
		else{
			return (User)list.get(0);
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
}
