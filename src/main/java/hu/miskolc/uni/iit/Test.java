package hu.miskolc.uni.iit;

import java.io.IOException;
import java.util.Arrays;

import hu.miskolc.uni.iit.model.Product;
import hu.miskolc.uni.iit.persist.ProductDAOImpl;
import hu.miskolc.uni.iit.service.ProductService;
import hu.miskolc.uni.iit.service.ProductServiceMyBatisImpl;

public class Test {

	public static void main(String[] args) throws IOException {

		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("ProductPersistenceUnit");
		//
		// EntityManager em = emf.createEntityManager();
		//
		// Query query = em.createNamedQuery("Product.lastInsertId");
		//
		// query = em.createNativeQuery("SELECT last_insert_id() FROM DUAL");
		//
		// System.out.println(query.getSingleResult());
		//
		// Product product = new Product();
		// product.setName("hello");
		//
		// em.getTransaction().begin();
		//
		// em.merge(product);
		//
		// em.getTransaction().commit();
		//
		// query = em.createNamedQuery("Product.lastInsertId");
		//
		// System.out.println(query.getSingleResult());
		//
		// em.close();
		//
		// emf.close();

		ProductService service = new ProductServiceMyBatisImpl(new ProductDAOImpl());

		for (Product p : service.searchAll()) {
			System.out.println(p);
		}

		Product product = new Product();
		product.setName("hello");
		product.setParts(Arrays.asList(service.searchById(9)));
		System.out.println(service.save(product));

		for (Product p : service.searchAll()) {
			System.err.println(p);
		}

	}

}
