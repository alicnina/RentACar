/**
 * Soap11BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.policeregistersimulator;

import javax.xml.rpc.holders.StringHolder;

import com.alicnina.paymentsimulator.DAOInterface;

public class Soap11BindingImpl implements com.alicnina.policeregistersimulator.PortType{
	
	private DAOInterface<Register> registerDAO;

	public void setRegisterDAO(DAOInterface<Register> registerDAO) {
		this.registerDAO = registerDAO;
	}
	
    public void initializePoliceRegister(java.lang.String IDNumber, java.lang.String drivingLicenceNumber, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException {
    	Register register = registerDAO.findByKeyWords(IDNumber, drivingLicenceNumber);
		if (register == null) {
			code = new StringHolder("100");
			message = new StringHolder("User with this ID Number and Driving Licence Number does not exist !");
		}
		
		else {
			code = new StringHolder("101");
			message = new StringHolder("User exists. ");
		}

    }

}
