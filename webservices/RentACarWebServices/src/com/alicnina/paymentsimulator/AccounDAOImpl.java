package com.alicnina.paymentsimulator;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AccounDAOImpl implements DAOInterface<Account> {
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	// this method fins account by id
	 public Account findByID(int ID){
			List<?> list = hibernateTemplate.find("from Account where Account_ID = ?",ID);
			return (Account)list.get(0);
	 }
	 
	 // this method fins account by creditCardNumber and cvv2
	 public Account findByKeyWords(String creditCardNumber, String cvv2) {
			List<?> list = hibernateTemplate.find("from Account where Credit_Card_Number = ? AND Cvv2 = ?", creditCardNumber, cvv2);
			if(list.isEmpty()){
				return null;
			}
			else{
				return (Account)list.get(0);
			}
		}
}
