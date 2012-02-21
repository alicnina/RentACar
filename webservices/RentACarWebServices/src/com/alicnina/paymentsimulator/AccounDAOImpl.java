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

	private PersistenceManagerFactory getPersistanceManagerFactory() {
		if (null == pmfo) {
			pmfo = JDOHelper.getPersistenceManagerFactory("oodb.properties");
		}

		return pmfo;
	}

	// this method fins account by id
	public Account findByID(String acc) throws Exception {
		throw new Exception("This method is not supported!");
	}

	// this method fins account by creditCardNumber and cvv2
	public Account findByKeyWords(String creditCardNumber, String cvv2) {
		PersistenceManager pm = getPersistanceManagerFactory().getPersistenceManager();
		Extent<Account> ext = pm.getExtent(Account.class, true);
		Query q = pm.newQuery(ext);
		Collection<Account> c = (Collection<Account>) q.execute();
		Iterator<Account> iter = c.iterator();
		while (iter.hasNext()) {
			Account a = iter.next();
			if (a.getCreditCardNo().equals(creditCardNumber) && a.getCvv2().equals(cvv2)) {
				return a;
			}
		}
		pm.close();
		return null;
	}

	public boolean save(Account obj) {
		try {
			PersistenceManager pm = getPersistanceManagerFactory().getPersistenceManager();
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
	public boolean delete(Account obj) {
		try {
			Account acc = findByKeyWords(obj.getCreditCardNo(), obj.getCvv2());
			if (null != acc) {
				PersistenceManager pm = JDOHelper.getPersistenceManager(acc);
				Transaction transo = pm.currentTransaction();
				transo.begin();
				pm.deletePersistent(acc);
				transo.commit();
				pm.close();
			}
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
