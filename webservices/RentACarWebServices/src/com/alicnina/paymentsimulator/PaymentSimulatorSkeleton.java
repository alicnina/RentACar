/**
 * PaymentSimulatorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.alicnina.paymentsimulator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;

/**
 * PaymentSimulatorSkeleton java skeleton for the axisService
 */
public class PaymentSimulatorSkeleton {

	private DAOInterface<Account> accountDAO = new AccounDAOImpl();

	/**
	 * Auto generated method signature
	 * 
	 * @param initializePayment
	 * @return initializePaymentResponse
	 */

	public OMElement initializePayment(OMElement request) {

		try {
			InitializePayment initializePayment = InitializePayment.Factory.parse(request.getXMLStreamReaderWithoutCaching());

			InitializePaymentResponse response = new InitializePaymentResponse();

			double ammount = initializePayment.getAmmount();

			Account account = accountDAO.findByKeyWords(initializePayment.getCreditCardNo(), initializePayment.getCvv2());
			if (account == null) {
				response.setCode("100");
				response.setMessage("Account with this Credit Card Number " + initializePayment.getCreditCardNo() + " and CVV2 " + initializePayment.getCvv2()
						+ " does NOT exist!");

			}

			else if (account.getAmmount() < ammount) {
				response.setCode("102");
				response.setMessage("Account does not have enough money for transaction!");
			}

			else {
				double newAccountBalance = account.getAmmount() - ammount;
				account.setAmmount(newAccountBalance);
				boolean isDeleted = accountDAO.delete(account);
				if (isDeleted) {
					accountDAO.save(account);
					response.setCode("101");
					response.setMessage("You've successfuly called Online Payment Simulator!");
				}
			}

			return response.getOMElement(InitializePaymentResponse.MY_QNAME, OMAbstractFactory.getOMFactory());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeAccount
	 * @return removeAccountResponse
	 */

	public OMElement removeAccount(OMElement request) {
		try {
			RemoveAccount removeAccount = RemoveAccount.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			Account account = new Account();
			account.setCreditCardNo(removeAccount.getCreditCardNo());
			account.setCvv2(removeAccount.getCvv2());
			accountDAO.delete(account);
			RemoveAccountResponse response = new RemoveAccountResponse();
			response.setCode("102");
			response.setMessage("You've successfuly REMOVED account with credit card number = " + removeAccount.getCreditCardNo());
			return response.getOMElement(RemoveAccountResponse.MY_QNAME, OMAbstractFactory.getOMFactory());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param registerAccount
	 * @return registerAccountResponse
	 */

	public OMElement registerAccount(OMElement request) {
		try {
			RegisterAccount registerAccount = RegisterAccount.Factory.parse(request.getXMLStreamReaderWithoutCaching());
			Account account = new Account();
			account.setCreditCardNo(registerAccount.getCreditCardNo());
			account.setCvv2(registerAccount.getCvv2());
			account.setAmmount(registerAccount.getAmmount());
			accountDAO.save(account);
			RegisterAccountResponse response = new RegisterAccountResponse();
			response.setCode("100");
			response.setMessage("You've successfuly SAVED account with credit card number = " + registerAccount.getCreditCardNo());
			return response.getOMElement(RegisterAccountResponse.MY_QNAME, OMAbstractFactory.getOMFactory());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
