package com.wf.ivankov.shop.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Ivankov_A
 *
 */
@Entity
public class Product {

	public Product() {
	}

	public Product(Integer id, String name, BigDecimal cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private BigDecimal cost;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
