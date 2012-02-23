package com.alicnina.policeregistersimulator;

import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class RegisterDAOImpl implements com.alicnina.paymentsimulator.DAOInterface<Register> {

	private static PersistenceManagerFactory pmfo;
	private PersistenceManager pm;

	private PersistenceManagerFactory getPersistenceManagerFactory() {

		if (null == pmfo) {
			pmfo = JDOHelper.getPersistenceManagerFactory("oodbregister.properties");
		}
		return pmfo;
	}

	// this method fins account by id
	public Register findByID(String reg) throws Exception {
		boolean closePm = false;
		if (null == pm) {
			pm = getPersistenceManagerFactory().getPersistenceManager();
			closePm = true;
		}
		Transaction transo = pm.currentTransaction();
		try {
			transo.begin();
			Extent<Register> ext = pm.getExtent(Register.class, true);
			Query q = pm.newQuery(ext);
			Collection<Register> c = (Collection<Register>) q.execute();
			Iterator<Register> iter = c.iterator();
			while (iter.hasNext()) {
				Register r = iter.next();
				if (r.getDrivingLicenceNumber().equals(reg)) {
					transo.commit();
					return r;
				}
			}
			transo.commit();
		} finally {

			if (null != transo && transo.isActive()) {
				transo.rollback();
			}
			if (closePm) {
				pm.close();
				pm = null;
			}
		}
		return null;
	}

	// this method fins account by creditCardNumber and cvv2
	public Register findByKeyWords(String IDNumber, String drivingLicenceNumber) {
		boolean closePm = false;
		if (null == pm) {
			pm = getPersistenceManagerFactory().getPersistenceManager();
			closePm = true;
		}
		Transaction currentTransaction = null;

		try {
			currentTransaction = pm.currentTransaction();
			currentTransaction.begin();
			Extent<Register> ext = pm.getExtent(Register.class, true);
			Query q = pm.newQuery(ext);
			Collection<Register> c = (Collection<Register>) q.execute();
			Iterator<Register> iter = c.iterator();
			while (iter.hasNext()) {
				Register r = iter.next();
				if (r.getIdNumber().equals(IDNumber) && r.getDrivingLicenceNumber().equals(drivingLicenceNumber)) {
					currentTransaction.commit();
					return r;
				}
			}
			currentTransaction.commit();
		} finally {

			if (null != currentTransaction && currentTransaction.isActive()) {
				currentTransaction.rollback();
			}
			if (closePm) {
				pm.close();
				pm = null;
			}
		}
		return null;
	}

	@Override
	public boolean save(Register obj) {
		pm = getPersistenceManagerFactory().getPersistenceManager();
		Transaction transo = null;
		try {
			transo = pm.currentTransaction();
			transo.begin();
			pm.makePersistent(obj);
			transo.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		} finally {

			if (null != transo && transo.isActive()) {
				transo.rollback();
			}
			pm.close();
			pm = null;
		}

	}

	@Override
	public boolean delete(Register obj) {
		pm = getPersistenceManagerFactory().getPersistenceManager();
		Transaction transo = null;
		try {
			Register reg = findByKeyWords(obj.getIdNumber(), obj.getDrivingLicenceNumber());
			if (null != reg) {
				transo = pm.currentTransaction();
				transo.begin();
				pm.deletePersistent(reg);
				transo.commit();
			}
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		} finally {

			if (null != transo && transo.isActive()) {
				transo.rollback();
			}
			pm.close();
			pm = null;
		}
	}

}
