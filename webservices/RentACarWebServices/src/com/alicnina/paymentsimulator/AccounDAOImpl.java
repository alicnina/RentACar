package com.alicnina.paymentsimulator;

import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class AccounDAOImpl implements DAOInterface<Account> {

	private PersistenceManagerFactory pmfo = null;
	private PersistenceManager pm;

	private PersistenceManagerFactory getPersistanceManagerFactory() {
		if (null == pmfo) {
			pmfo = JDOHelper.getPersistenceManagerFactory("oodbpayment.properties");
		}

		return pmfo;
	}

	// this method fins account by id
	public Account findByID(String acc) throws Exception {
		throw new Exception("This method is not supported!");
	}

	// this method fins account by creditCardNumber and cvv2
	public Account findByKeyWords(String creditCardNumber, String cvv2) {
		boolean closePm = false;

		if (null == pm) {
			pm = getPersistanceManagerFactory().getPersistenceManager();
			closePm = true;
		}

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Account> ext = pm.getExtent(Account.class, true);
			Query q = pm.newQuery(ext);
			Collection<Account> c = (Collection<Account>) q.execute();
			Iterator<Account> iter = c.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getCreditCardNo().equals(creditCardNumber) && a.getCvv2().equals(cvv2)) {
					tx.commit();
					return a;
				}
			}
			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			if (closePm) {
				pm.close();
				pm = null;
			}
		}
		return null;
	}

	public boolean save(Account obj) {

		pm = getPersistanceManagerFactory().getPersistenceManager();
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
	public boolean delete(Account obj) {

		pm = getPersistanceManagerFactory().getPersistenceManager();
		Transaction transo = null;
		try {
			Account acc = findByKeyWords(obj.getCreditCardNo(), obj.getCvv2());
			if (null != acc) {
				transo = pm.currentTransaction();
				transo.begin();
				pm.deletePersistent(acc);
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
