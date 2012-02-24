package etf.eminaa.dao;

import java.util.List;

public interface DAOInterface<T> {

	public void save(T user);

	public List<T> list();

	public List<T> list(String criteria);

	public void edit(T user);

	public void delete(T user);

	T findByPrimaryKey(Object key);

	/**
	 * Abstract method to search by keywords in WHERE clause<br/>
	 * example: <br />
	 * args = {"column1 = val1", "column2 = val2"}
	 * @param operator can be "AND" or "OR"
	 * @param args
	 * @return
	 */
	List<T> findByKeyWords(String operator, String... args);
}
