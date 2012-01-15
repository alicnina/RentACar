package com.alicnina.paymentsimulator;

public interface DAOInterface<T> {
	
	T findByID(int ID);
	T findByKeyWords(String creditCardNumber, String cvv2);
}
