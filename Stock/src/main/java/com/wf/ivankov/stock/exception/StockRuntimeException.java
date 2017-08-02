/**
 * 
 */
package com.wf.ivankov.stock.exception;

import com.wf.ivankov.common.exception.CommonWfRuntimeException;

/**
 * @author Ivankov_A
 *
 */
public class StockRuntimeException extends CommonWfRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2456816826768950899L;

	public StockRuntimeException(String message) {
		super(message);
	}
}
