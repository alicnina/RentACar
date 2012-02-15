package com.alicnina.policeregistersimulator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.alicnina.paymentsimulator.Account;

public class RegisterDAOImpl implements com.alicnina.paymentsimulator.DAOInterface<Register> {

	private PersistenceManager pm;

	// this method fins account by id
	public Register findByID(String reg) throws Exception {
		PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("oodb.properties");
		pm = pmfo.getPersistenceManager();
		Transaction transo = pm.currentTransaction();
		transo.begin();
		Extent<Register> ext = pm.getExtent(Register.class, true);
		Query q = pm.newQuery(ext);
		Collection<Register> c = (Collection<Register>) q.execute();
		Iterator<Register> iter = c.iterator();
		while(iter.hasNext()) {
			Register r = iter.next();
			if(r.getDrivingLicenceNumber().equals(reg)) {
				return r;
			}
		}
		transo.commit();
		pm.close();
		return null;
	}

	// this method fins account by creditCardNumber and cvv2
	public Register findByKeyWords(String IDNumber, String drivingLicenceNumber) {
		PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("oodb.properties");
		// PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("mysql.properties");
		pm = pmfo.getPersistenceManager();
		Transaction transo = pm.currentTransaction();
		transo.begin();
		Extent<Register> ext = pm.getExtent(Register.class, true);
		Query q = pm.newQuery(ext);
		Collection<Register> c = (Collection<Register>) q.execute();
		Iterator<Register> iter = c.iterator();
		while (iter.hasNext()) {
			Register r = iter.next();
			if (r.getIdNumber().equals(IDNumber) && r.getDrivingLicenceNumber().equals(drivingLicenceNumber)) {
				return r;
			}
		}
		transo.commit();
		pm.close();
		return null;
	}

	@Override
	public boolean save(Register obj) {
		try {
		// PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("oodb.properties");
		PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("mysql.properties");
		pm = pmfo.getPersistenceManager();
		Transaction transo = pm.currentTransaction();
		transo.begin();
		pm.makePersistent(obj);
		transo.commit();
		pm.close();
		return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean delete(Register obj) {
		try {
			Register reg = findByKeyWords(obj.getIdNumber(), obj.getDrivingLicenceNumber());
			// PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("oodb.properties");
			PersistenceManagerFactory pmfo = JDOHelper.getPersistenceManagerFactory("mysql.properties");
			pm = pmfo.getPersistenceManager();
			Transaction transo = pm.currentTransaction();
			transo.begin();
			pm.deletePersistent(reg);
			transo.commit();
			pm.close();
			return true;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				return false;
			}
	}

}
