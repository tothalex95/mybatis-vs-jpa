package hu.miskolc.uni.iit.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hu.miskolc.uni.iit.model.Product;

public interface ProductMapper {

	List<Product> selectAll();

	Product selectById(@Param("id") int id);

	int selectLastInsertId();

	void insert(@Param("product") Product product);

	void insertParts(@Param("id") int id, @Param("parts") List<Product> parts);

	void update(@Param("product") Product product);

	void delete(@Param("id") int id);

	void deleteParts(@Param("id") int id);

}
