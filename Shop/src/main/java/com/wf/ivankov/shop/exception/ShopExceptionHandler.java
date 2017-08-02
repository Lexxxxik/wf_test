/**
 * 
 */
package com.wf.ivankov.shop.exception;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wf.ivankov.common.exception.ExceptionRestResponce;

/**
 * @author Ivankov_A
 *
 */
@ControllerAdvice
public class ShopExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ShopExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public ExceptionRestResponce defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		if (e instanceof NoSuchElementException) {
			throw new NoSuchElementFoundException("No such Element Found");
		}
		
		LOG.warn("Exception: {}", e);
		return new ExceptionRestResponce(e.getMessage());
	}
}
