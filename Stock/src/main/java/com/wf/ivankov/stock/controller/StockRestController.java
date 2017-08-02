package com.wf.ivankov.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ivankov.common.annotation.CountEnchancer;
import com.wf.ivankov.common.domain.ProductReservation;
import com.wf.ivankov.common.domain.ProductStock;
import com.wf.ivankov.stock.service.ProductReservationService;
import com.wf.ivankov.stock.validation.ProductReservationBeanValidator;

/**
 * @author Ivankov_A
 *
 */
@RestController
@RequestMapping("/stock")
public class StockRestController {

	private static final Logger LOG = LoggerFactory.getLogger(StockRestController.class);

	@Autowired
	private ProductReservationService reservationService;
	@Autowired
	private ProductReservationBeanValidator prValidator; 

	@RequestMapping(path = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<ProductStock> getStock(@PathVariable int productId) {
		return new ResponseEntity<ProductStock>(reservationService.getStock(productId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductStock> save(@RequestBody ProductStock bean) {
		return new ResponseEntity<ProductStock>(reservationService.save(bean), HttpStatus.CREATED);
	}

	@RequestMapping(path = "/reserve", method = RequestMethod.POST)
	public ResponseEntity<ProductReservation> getReservation(@CountEnchancer(99) @RequestBody ProductReservation reservation, BindingResult result) {
		LOG.info("getReservation {} ", reservation);
		prValidator.validate(reservation, result);
		if (result.hasErrors()) {
			return new ResponseEntity<ProductReservation>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			reservationService.reserveProduct(reservation);
		}
		return new ResponseEntity<ProductReservation>(reservation, HttpStatus.OK);
	}
}
