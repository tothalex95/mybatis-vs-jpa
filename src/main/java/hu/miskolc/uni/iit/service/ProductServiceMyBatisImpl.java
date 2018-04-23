package hu.miskolc.uni.iit.service;

import java.util.List;

import hu.miskolc.uni.iit.model.Product;
import hu.miskolc.uni.iit.persist.ProductDAO;

public class ProductServiceMyBatisImpl implements ProductService {

	private ProductDAO productDAO;

	public ProductServiceMyBatisImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public Integer create(Product product) {
		return productDAO.save(product);
	}

	@Override
	public List<Product> readAll() {
		return productDAO.findAll();
	}

	@Override
	public Product readById(Integer id) {
		return productDAO.findOne(id);
	}

}
