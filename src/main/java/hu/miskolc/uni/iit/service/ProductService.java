package hu.miskolc.uni.iit.service;

import java.util.List;

import hu.miskolc.uni.iit.model.Product;

public interface ProductService {

	Integer create(Product product);

	List<Product> readAll();

	Product readById(Integer id);

}
