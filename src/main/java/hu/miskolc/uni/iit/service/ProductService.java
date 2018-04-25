package hu.miskolc.uni.iit.service;

import java.util.List;

import hu.miskolc.uni.iit.model.Product;

public interface ProductService {

	Integer save(Product product);

	List<Product> searchAll();

	Product searchById(Integer id);

	boolean delete(Integer id);

}
