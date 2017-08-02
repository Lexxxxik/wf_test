/**
 * 
 */
package com.wf.ivankov.stock.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;;

/**
 * @author Ivankov_A
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such ProductReservation") // 404
public class ProductReservationNotFoundException extends StockRuntimeException {

	private static final long serialVersionUID = 875924563772763695L;

	public ProductReservationNotFoundException(String message) {
		super(message);
	}

}
