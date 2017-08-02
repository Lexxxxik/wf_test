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
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such ProductStock") // 404
public class ProductStockNotFoundException extends StockRuntimeException {

	private static final long serialVersionUID = 286030879612231933L;

	public ProductStockNotFoundException(String message) {
        super(message);
    }
}
