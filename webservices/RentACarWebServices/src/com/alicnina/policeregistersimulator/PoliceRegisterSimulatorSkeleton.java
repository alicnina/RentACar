/**
 * PoliceRegisterSimulatorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.alicnina.policeregistersimulator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;

import com.alicnina.paymentsimulator.Account;
import com.alicnina.paymentsimulator.DAOInterface;
import com.alicnina.policeregistersimulator.InitializePoliceRegister;
import com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.Register;

/**
 * PoliceRegisterSimulatorSkeleton java skeleton for the axisService
 */
public class PoliceRegisterSimulatorSkeleton {

	private DAOInterface<Register> registerDAO;

	public DAOInterface<Register> getRegisterDAO() {
		return registerDAO;
	}

	public void setRegisterDAO(DAOInterface<Register> registerDAO) {
		this.registerDAO = registerDAO;
	}
	/**
	 * Auto generated method signature
	 * 
	 * @param initializePoliceRegister
	 * @return initializePoliceRegisterResponse
	 */

	public OMElement initializePoliceRegister(OMElement request) {
		try {

			InitializePoliceRegister initializePoliceRegister = InitializePoliceRegister.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			InitializePoliceRegisterResponse response = new InitializePoliceRegisterResponse();

			Register register = registerDAO.findByKeyWords(initializePoliceRegister.getIDNumber(), initializePoliceRegister.getDrivingLicenceNumber());
			if (register == null) {
				response.setCode("100");
				response.setMessage("User with this ID Number and Driving Licence Number does not exist !");
			}

			else {
				response.setCode("101");
				response.setMessage("User exists.");
			}

			return response.getOMElement(InitializePoliceRegisterResponse.MY_QNAME, OMAbstractFactory.getOMFactory());

		} catch (Exception e) {
			// TODO parsing of request error!
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param savePoliceRegister
	 * @return savePoliceRegisterResponse
	 */

	public OMElement savePoliceRegister(OMElement request) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#savePoliceRegister");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removePoliceRegister
	 * @return removePoliceRegisterResponse
	 */

	public OMElement removePoliceRegister(OMElement request) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#removePoliceRegister");
	}

}
