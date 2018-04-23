package hu.miskolc.uni.iit.persist;

import java.util.List;

/**
 * @author Alex Toth
 *
 * @param <T>
 */
public interface BaseDAO<T> {

	/**
	 * Returns all objects of type T stored in the database.
	 * 
	 * @return Every object of type T.
	 */
	List<T> findAll();

	/**
	 * Returns the object of type T identified by {@link id}.
	 * 
	 * @param id
	 *            The id of the object that has to be returned.
	 * @return The object identified by {@link id}.
	 */
	T findOne(Integer id);

	/**
	 * Persists an object. Performs a create when the {@link object} id is null,
	 * otherwise updates it.
	 * 
	 * @param object
	 *            The object that has to be stored in the database.
	 * @return The id of stored object.
	 */
	Integer save(T object);

}
