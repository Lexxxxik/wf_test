package com.wf.ivankov.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ivankov.common.domain.ProductReservation;
import com.wf.ivankov.common.domain.ProductStock;
import com.wf.ivankov.shop.domain.Product;
import com.wf.ivankov.shop.service.ProductService;
import com.wf.ivankov.shop.service.StockService;

/**
 * @author Ivankov_A
 *
 */
@RestController
@RequestMapping("/product")
public class ShopRestController {

	@Autowired
	@Qualifier("testProductService")
	private ProductService productService;
	
	@Autowired
	private StockService stockService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAll() {
		return new ResponseEntity<List<Product>>(productService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getOne(@PathVariable Integer id) {
		return new ResponseEntity<Product>(productService.getOne(id), HttpStatus.OK);
	}

	@RequestMapping(path = "/search/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> searchByName(@PathVariable String name) {
		return new ResponseEntity<List<Product>>(productService.searchByName(name), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> save(@RequestBody Product bean) {
		return new ResponseEntity<Product>(productService.save(bean), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Product> saveUpdate(@RequestBody Product bean) {
		return new ResponseEntity<Product>(productService.save(bean), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> delete(@PathVariable Integer id) {
		productService.delete(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@RequestMapping(path = "/stock/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductStock> getStock(@PathVariable Integer id) {
		return new ResponseEntity<ProductStock>(stockService.getStock(id), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/stock/reserve", method = RequestMethod.POST)
	public ResponseEntity<ProductReservation> getStock(@RequestBody ProductReservation productStock) {
		stockService.reserveFromStock(productStock);
		return new ResponseEntity<ProductReservation>(HttpStatus.OK);
	}
}