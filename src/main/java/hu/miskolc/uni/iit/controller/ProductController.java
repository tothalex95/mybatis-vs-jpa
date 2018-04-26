package hu.miskolc.uni.iit.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.miskolc.uni.iit.request.DeleteProductRequest;
import hu.miskolc.uni.iit.request.ORMType;
import hu.miskolc.uni.iit.request.SaveProductRequest;
import hu.miskolc.uni.iit.request.SearchProductRequest;
import hu.miskolc.uni.iit.response.DeleteProductResponse;
import hu.miskolc.uni.iit.response.SaveProductResponse;
import hu.miskolc.uni.iit.response.SearchProductResponse;
import hu.miskolc.uni.iit.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping(value = { "/product" })
public class ProductController {

	@Autowired
	private ProductService productServiceMyBatis;

	@Autowired
	private ProductService productServiceJPA;

	@PostMapping(value = { "/save" }, produces = { "application/json" })
	public @ResponseBody SaveProductResponse saveProduct(@RequestBody SaveProductRequest saveProductRequest) {
		SaveProductResponse saveProductResponse = new SaveProductResponse();
		saveProductResponse.setElapsedTime(new Date().getTime());

		System.out.println(saveProductRequest);

		if (saveProductRequest.getOrmType() == ORMType.MyBatis) {
			saveProductResponse.setId(productServiceMyBatis.save(saveProductRequest.getProduct()));
		} else {
			saveProductResponse.setId(productServiceJPA.save(saveProductRequest.getProduct()));
		}

		saveProductResponse.setElapsedTime((saveProductResponse.getElapsedTime() - new Date().getTime()) * -1);

		System.out.println(saveProductResponse);

		return saveProductResponse;
	}

	@GetMapping(value = { "/search" }, produces = { "application/json" })
	public @ResponseBody SearchProductResponse readProduct(SearchProductRequest searchProductRequest) {
		SearchProductResponse searchProductResponse = new SearchProductResponse();
		searchProductResponse.setElapsedTime(new Date().getTime());

		System.out.println(searchProductRequest);

		if (searchProductRequest.getOrmType() == ORMType.MyBatis) {
			if (searchProductRequest.getId() == null || searchProductRequest.getId() == 0) {
				searchProductResponse.getProducts().addAll(productServiceMyBatis.searchAll());
			} else {
				searchProductResponse.getProducts().add(productServiceMyBatis.searchById(searchProductRequest.getId()));
			}
		} else {
			if (searchProductRequest.getId() == null || searchProductRequest.getId() == 0) {
				searchProductResponse.getProducts().addAll(productServiceJPA.searchAll());
			} else {
				searchProductResponse.getProducts().add(productServiceJPA.searchById(searchProductRequest.getId()));
			}
		}

		searchProductResponse.setElapsedTime((searchProductResponse.getElapsedTime() - new Date().getTime()) * -1);

		System.out.println(searchProductResponse);

		return searchProductResponse;
	}

	@DeleteMapping(value = { "/delete" }, produces = { "application/json" })
	public @ResponseBody DeleteProductResponse deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
		DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
		deleteProductResponse.setElapsedTime(new Date().getTime());

		System.out.println(deleteProductRequest);

		if (deleteProductRequest.getOrmType() == ORMType.MyBatis) {
			deleteProductResponse.setSucceeded(productServiceMyBatis.delete(deleteProductRequest.getId()));
		} else {
			deleteProductResponse.setSucceeded(productServiceJPA.delete(deleteProductRequest.getId()));
		}

		deleteProductResponse.setElapsedTime((deleteProductResponse.getElapsedTime() - new Date().getTime()) * -1);

		System.out.println(deleteProductResponse);

		return deleteProductResponse;
	}

}
