/**
 * Soap11BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.paymentsimulator;

import javax.xml.rpc.holders.StringHolder;

public class Soap11BindingImpl implements com.alicnina.paymentsimulator.PortType{
	
	private DAOInterface<Account> accountDAO;

	public void setAccountDAO(DAOInterface<Account> accountDAO) {
		this.accountDAO = accountDAO;
	}
	
    public void initializePayment(java.lang.String creditCardNo, java.lang.String cvv2, double ammount, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {

    	Account account = accountDAO.findByKeyWords(creditCardNo, cvv2);
		if (account == null) {
			code = new StringHolder("100");
			message = new StringHolder("Account with this Credit Card Number and CVV2 does not exist !");
		}
		
		else if (account.getAmmount() < ammount) {
			code = new StringHolder("101");
			message = new StringHolder("Account does not have enough money for transaction!");
		}
		
		else {
			double newAccountBalance = account.getAmmount() - ammount;
			account.setAmmount(newAccountBalance);
			code = new StringHolder("102");
			message = new StringHolder("You've successfuly called Online Payment Simulator!");
		}
    }

}
