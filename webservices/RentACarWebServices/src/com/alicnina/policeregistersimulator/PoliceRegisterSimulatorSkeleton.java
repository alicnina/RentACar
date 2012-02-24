/**
 * PoliceRegisterSimulatorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.alicnina.policeregistersimulator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;

import com.alicnina.paymentsimulator.DAOInterface;
import com.alicnina.paymentsimulator.RegisterAccountResponse;

/**
 * PoliceRegisterSimulatorSkeleton java skeleton for the axisService
 */
public class PoliceRegisterSimulatorSkeleton {

	private DAOInterface<Register> registerDAO = new RegisterDAOImpl();

	/**
	 * Auto generated method signature
	 * 
	 * @param disablePoliceRegisterResponse
	 * @return disablePoliceRegisterResponse0
	 */

	public OMElement disablePoliceRegister(OMElement request) {
		try {
			DisablePoliceRegister disableReg = DisablePoliceRegister.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			DisablePoliceRegisterResponse response = new DisablePoliceRegisterResponse();

			Register register = registerDAO.findByID(disableReg.getDrivingLicenceNumber());
			register.setEnabledRegister(true);
			registerDAO.delete(register);
			registerDAO.save(register);

			response.setCode("300");
			response.setMessage("You've successfuly disabled this account!");

			return response.getOMElement(DisablePoliceRegisterResponse.MY_QNAME, OMAbstractFactory.getOMFactory());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param enablePoliceRegister
	 * @return enablePoliceRegister1
	 */

	public OMElement enablePoliceRegister(OMElement request) {
		try {
			EnablePoliceRegister enableReg = EnablePoliceRegister.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			EnablePoliceRegisterResponse response = new EnablePoliceRegisterResponse();

			Register reg = registerDAO.findByID(enableReg.getDrivingLicenceNumber());
			reg.setEnabledRegister(false);
			registerDAO.delete(reg);
			registerDAO.save(reg);

			response.setCode("303");
			response.setMessage("You've successfuly enabled this account!");

			return response.getOMElement(EnablePoliceRegisterResponse.MY_QNAME, OMAbstractFactory.getOMFactory());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
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

			Register register = registerDAO.findByKeyWords(initializePoliceRegister.getIdNumber(), initializePoliceRegister.getDrivingLicenceNumber());
			if (register == null) {
				response.setCode("100");
				response.setMessage("User with this ID Number and Driving Licence Number does not exist !");
			}

			else if (register.isEnabledRegister() == true) {
				response.setCode("101");
				response.setMessage("User is allowed to rent a car.");
			} else if (register.isEnabledRegister() == false) {
				response.setCode("102");
				response.setMessage("User is forbidden to access the system.");
			}

			return response.getOMElement(InitializePoliceRegisterResponse.MY_QNAME, OMAbstractFactory.getOMFactory());

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @return savePoliceRegisterResponse
	 */

	public OMElement savePoliceRegister(OMElement request) {

		try {
			SavePoliceRegister saveRegister = SavePoliceRegister.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			Register reg = new Register();
			reg.setIdNumber(saveRegister.getIdNumber());
			reg.setDrivingLicenceNumber(saveRegister.getDrivingLicenceNumber());
			reg.setEnabledRegister(saveRegister.getEnabledRegister());
			registerDAO.save(reg);
			SavePoliceRegisterResponse response = new SavePoliceRegisterResponse();
			response.setCode("200");
			response.setMessage("You've successfuly saved this account!");
			return response.getOMElement(RegisterAccountResponse.MY_QNAME, OMAbstractFactory.getOMFactory());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removePoliceRegister
	 * @return removePoliceRegisterResponse
	 */

	public OMElement removePoliceRegister(OMElement request) {
		try {
			RemovePoliceRegister removeReg = RemovePoliceRegister.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			Register reg = new Register();
			reg.setIdNumber(removeReg.getIdNumber());
			reg.setDrivingLicenceNumber(removeReg.getDrivingLicenceNumber());
			registerDAO.delete(reg);
			RemovePoliceRegisterResponse response = new RemovePoliceRegisterResponse();
			response.setCode("202");
			response.setMessage("You've successfuly removed this account!");
			return response.getOMElement(RemovePoliceRegisterResponse.MY_QNAME, OMAbstractFactory.getOMFactory());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
