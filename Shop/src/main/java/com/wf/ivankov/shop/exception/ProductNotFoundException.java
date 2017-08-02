/**
 * 
 */
package com.wf.ivankov.shop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;;

/**
 * @author Ivankov_A
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Product") // 404
public class ProductNotFoundException extends NoSuchElementFoundException {

	private static final long serialVersionUID = 4276536134579346524L;

	public ProductNotFoundException(String message) {
		super(message);
	}


}
