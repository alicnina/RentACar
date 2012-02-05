package etf.eminaa.dao;

import java.util.List;

public interface DAOInterface<T> {

	public void save(T user);

	public List<T> list();

	public List<T> list(String criteria);

	public void edit(T user);

	public void delete(T user);

	T findByPrimaryKey(Object key);

	T findByKeyWords(String Username, String Password);
}
