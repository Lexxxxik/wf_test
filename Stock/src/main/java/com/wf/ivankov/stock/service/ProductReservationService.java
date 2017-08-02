package com.wf.ivankov.stock.service;

import com.wf.ivankov.common.domain.ProductReservation;
import com.wf.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
public interface ProductReservationService {

	ProductStock getStock(int productId);

	void reserveProduct(ProductReservation reservation);

	ProductStock save(ProductStock bean);
}
