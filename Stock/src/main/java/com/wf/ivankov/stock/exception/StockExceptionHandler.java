/**
 * 
 */
package com.wf.ivankov.stock.exception;

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
public class StockExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(StockExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public ExceptionRestResponce defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		LOG.warn("Exception: {}", e);
		return new ExceptionRestResponce(e.getMessage());
	}
}
