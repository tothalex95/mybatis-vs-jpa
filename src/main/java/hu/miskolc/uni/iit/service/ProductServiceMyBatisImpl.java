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
	public Integer save(Product product) {
		return productDAO.save(product);
	}

	@Override
	public List<Product> searchAll() {
		return productDAO.findAll();
	}

	@Override
	public Product searchById(Integer id) {
		return productDAO.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		return productDAO.delete(id);
	}

}
