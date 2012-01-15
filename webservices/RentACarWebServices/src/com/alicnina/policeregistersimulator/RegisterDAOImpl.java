package com.alicnina.policeregistersimulator;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class RegisterDAOImpl implements com.alicnina.paymentsimulator.DAOInterface<Register> {
	
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	// this method fins account by id
	 public Register findByID(int ID){
			List<?> list = hibernateTemplate.find("from Register where Register_ID = ?",ID);
			return (Register)list.get(0);
	 }
	 
	 // this method fins account by creditCardNumber and cvv2
	 public Register findByKeyWords(String IDNumber, String drivingLicenceNumber) {
			List<?> list = hibernateTemplate.find("from Register where ID_Number = ? AND Driving_Licence_Number = ?", IDNumber, drivingLicenceNumber);
			if(list.isEmpty()){
				return null;
			}
			else{
				return (Register)list.get(0);
			}
		}

}
