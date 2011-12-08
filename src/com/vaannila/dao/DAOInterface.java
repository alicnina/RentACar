package com.vaannila.dao;

import java.util.List;

public interface DAOInterface<T> {
	
	public void save(T user);
	public List<T> list();
	public void edit(T user);
	public void delete(T user);
	T findByID(int ID);
	T findByKeyWords(String Username, String Password);
}
