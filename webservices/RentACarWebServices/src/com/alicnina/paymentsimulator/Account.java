package com.alicnina.paymentsimulator;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Account {

	private String creditCardNo;
	private String cvv2;
	private double ammount;
 
	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

}
