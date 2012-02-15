package com.alicnina.paymentsimulator;

public interface DAOInterface<T> {
	
	T findByID(String obj) throws Exception;
	T findByKeyWords(String creditCardNumber, String cvv2);
	boolean save(T obj);
	boolean delete(T obj);
}
