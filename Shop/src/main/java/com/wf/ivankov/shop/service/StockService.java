package com.wf.ivankov.shop.service;

import com.wf.ivankov.common.domain.ProductReservation;
import com.wf.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
public interface StockService {

	ProductStock getStock(Integer productId);

	void reserveFromStock(ProductReservation reservation);
}
