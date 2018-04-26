package hu.miskolc.uni.iit.persist;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hu.miskolc.uni.iit.model.Product;
import hu.miskolc.uni.iit.persist.mapper.ProductMapper;

/**
 * @author Alex Toth
 *
 */
public class ProductDAOImpl implements ProductDAO {

	private SqlSessionFactory sqlSessionFactory;

	public ProductDAOImpl() throws IOException {
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

		String myBatisConfig = System.getProperty("myBatisConfig");

		sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream(myBatisConfig));
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#findAll()
	 */
	@Override
	public List<Product> findAll() {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();

			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

			List<Product> products = mapper.selectAll();

			// Will be removed when I find a better solution for collecting the parts.
			products.stream().forEach(product -> collectParts(mapper, product));

			return products;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#findOne(java.lang.Integer)
	 */
	@Override
	public Product findOne(Integer id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();

			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

			Product product = mapper.selectById(id);

			// Will be removed when I find a better solution for collecting the parts.
			if (product != null) {
				collectParts(mapper, product);
			}

			return product;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#save(java.lang.Object)
	 */
	@Override
	public Integer save(Product product) {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();

			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

			Integer id = null;

			if (product.getId() == null) {
				// The product doesn't exist, should be created.
				mapper.insert(product);
				id = mapper.selectLastInsertId();
			} else {
				// The product exists, should be updated.
				mapper.update(product);
				id = product.getId();
			}

			mapper.deleteParts(id);
			for (Product part : product.getParts()) {
				mapper.insertPart(id, part);
			}

			sqlSession.commit();

			return id;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * @see hu.miskolc.uni.iit.persist.BaseDAO#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) {
		// TODO: Fix it to be able to delete products that are parts of other products.
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();

			ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

			mapper.deleteParts(id);
			mapper.delete(id);

			sqlSession.commit();

			Product product = mapper.selectById(id);

			return product == null ? true : false;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * The parts of the products must be collected. I have to use this solution till
	 * I find a better one. I need a solution with using SQL only.
	 * 
	 * @param mapper
	 * @param product
	 */
	private void collectParts(ProductMapper mapper, Product product) {
		for (Product part : product.getParts()) {
			Product partFull = mapper.selectById(part.getId());

			partFull.getParts().stream().forEach(part.getParts()::add);

			collectParts(mapper, part);
		}
	}

}
