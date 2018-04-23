package hu.miskolc.uni.iit.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.miskolc.uni.iit.request.CreateProductRequest;
import hu.miskolc.uni.iit.request.DeleteProductRequest;
import hu.miskolc.uni.iit.request.ORMType;
import hu.miskolc.uni.iit.request.ReadProductRequest;
import hu.miskolc.uni.iit.request.UpdateProductRequest;
import hu.miskolc.uni.iit.response.CreateProductResponse;
import hu.miskolc.uni.iit.response.DeleteProductResponse;
import hu.miskolc.uni.iit.response.ReadProductResponse;
import hu.miskolc.uni.iit.response.UpdateProductResponse;
import hu.miskolc.uni.iit.service.ProductService;

@RestController
@RequestMapping(value = { "/product" })
public class ProductController {

	@Autowired
	private ProductService productServiceMyBatis;

	@Autowired
	private ProductService productServiceJPA;

	@PostMapping(value = { "/create" }, produces = { "application/json" })
	public @ResponseBody CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
		CreateProductResponse createProductResponse = new CreateProductResponse();
		createProductResponse.setElapsedTime(new Date().getTime());

		System.out.println(createProductRequest);

		if (createProductRequest.getOrmType() == ORMType.MyBatis) {
			createProductResponse.setId(productServiceMyBatis.create(createProductRequest.getProduct()));
		} else {
			createProductResponse.setId(productServiceJPA.create(createProductRequest.getProduct()));
		}

		createProductResponse.setElapsedTime((createProductResponse.getElapsedTime() - new Date().getTime()) * -1);

		System.out.println(createProductResponse);

		return createProductResponse;
	}

	@PostMapping(value = { "/read" }, produces = { "application/json" })
	public @ResponseBody ReadProductResponse readProduct(@RequestBody ReadProductRequest readProductRequest) {
		ReadProductResponse readProductResponse = new ReadProductResponse();
		readProductResponse.setElapsedTime(new Date().getTime());

		System.out.println(readProductRequest);

		if (readProductRequest.getOrmType() == ORMType.MyBatis) {
			if (readProductRequest.getId() == null) {
				readProductResponse.getProducts().addAll(productServiceMyBatis.readAll());
			} else {
				readProductResponse.getProducts().add(productServiceMyBatis.readById(readProductRequest.getId()));
			}
		} else {
			if (readProductRequest.getId() == null) {
				readProductResponse.getProducts().addAll(productServiceJPA.readAll());
			} else {
				readProductResponse.getProducts().add(productServiceJPA.readById(readProductRequest.getId()));
			}
		}

		readProductResponse.setElapsedTime((readProductResponse.getElapsedTime() - new Date().getTime()) * -1);

		System.out.println(readProductResponse);

		return readProductResponse;
	}

	@PostMapping(value = { "/update" }, produces = { "application/json" })
	public @ResponseBody UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
		UpdateProductResponse updateProductResponse = new UpdateProductResponse();

		return updateProductResponse;
	}

	@PostMapping(value = { "/delete" }, produces = { "application/json" })
	public @ResponseBody DeleteProductResponse deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
		DeleteProductResponse deleteProductResponse = new DeleteProductResponse();

		return deleteProductResponse;
	}

}
