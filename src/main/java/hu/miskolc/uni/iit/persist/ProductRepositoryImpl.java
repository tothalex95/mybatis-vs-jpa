package hu.miskolc.uni.iit.persist;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hu.miskolc.uni.iit.model.Product;

public class ProductRepositoryImpl implements ProductDAO {

	private EntityManagerFactory entityManagerFactory;

	public ProductRepositoryImpl() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ProductPersistenceUnit");
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("Product.findAll");

			List<Product> products = query.getResultList();

			return products;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#findOne(java.lang.Integer)
	 */
	@Override
	public Product findOne(Integer id) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			Product product = entityManager.find(Product.class, id);

			return product;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#save(java.lang.Object)
	 */
	@Override
	public Integer save(Product product) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			entityManager.getTransaction().begin();

			Product savedProduct = entityManager.merge(product);

			entityManager.getTransaction().commit();

			return savedProduct.getId();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) {
		// TODO: Fix it to be able to delete products that are parts of other products.
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			entityManager.getTransaction().begin();

			Product product = entityManager.find(Product.class, id);

			entityManager.remove(product);

			entityManager.getTransaction().commit();

			product = entityManager.find(Product.class, id);

			return product == null ? true : false;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

}
