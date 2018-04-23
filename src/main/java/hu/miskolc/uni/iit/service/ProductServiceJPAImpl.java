package hu.miskolc.uni.iit.service;

import java.util.List;

import hu.miskolc.uni.iit.model.Product;
import hu.miskolc.uni.iit.persist.ProductDAO;

public class ProductServiceJPAImpl implements ProductService {

	private ProductDAO productRepository;

	public ProductServiceJPAImpl(ProductDAO productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Integer create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> readAll() {
		return productRepository.findAll();
	}

	@Override
	public Product readById(Integer id) {
		return productRepository.findOne(id);
	}

}
