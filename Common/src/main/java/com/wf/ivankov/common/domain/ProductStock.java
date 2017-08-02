/**
 * 
 */
package com.wf.ivankov.common.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Ivankov_A
 *
 */
public class ProductStock {

	private Integer productId;
	private Integer quantity;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
