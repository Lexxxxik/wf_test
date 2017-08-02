package com.wf.ivankov.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ivankov_A
 *
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "No such User")
public class UserHasNoRolesException extends ShopRuntimeException {

	private static final long serialVersionUID = 1076355874877446091L;

	public UserHasNoRolesException(String message) {
		super(message);
	}


}
