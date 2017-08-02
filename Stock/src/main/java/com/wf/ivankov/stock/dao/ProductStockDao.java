package com.wf.ivankov.stock.dao;

import java.util.List;

import com.wf.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
public interface ProductStockDao {

	List<ProductStock> findAll();

	ProductStock getStock(int productId);

	void deleteAll();

	void insert(ProductStock bean);
	
	void update(ProductStock bean);
}
