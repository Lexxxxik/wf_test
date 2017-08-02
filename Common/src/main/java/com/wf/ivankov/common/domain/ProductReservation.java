/**
 * 
 */
package com.wf.ivankov.common.domain;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Ivankov_A
 *
 */
public class ProductReservation {

	private int productId;
	private int quantity;
	private String userName;
	private Timestamp reservationDate;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
