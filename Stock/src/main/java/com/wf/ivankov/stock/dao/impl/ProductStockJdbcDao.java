package com.wf.ivankov.stock.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.wf.ivankov.common.domain.ProductStock;
import com.wf.ivankov.stock.dao.ProductStockDao;
import com.wf.ivankov.stock.dao.ReflectionBeanRowMapper;
import com.wf.ivankov.stock.exception.ProductStockNotFoundException;

/**
 * @author Ivankov_A
 *
 */
@Repository
public class ProductStockJdbcDao implements ProductStockDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ProductStock");
	}

	@Override
	public List<ProductStock> findAll() {
		return jdbcTemplate.query("SELECT * FROM ProductStock", new ReflectionBeanRowMapper<ProductStock>(ProductStock.class));
	}

	@Override
	public ProductStock getStock(int productId) {
		List<ProductStock> l = jdbcTemplate.query("SELECT * FROM ProductStock WHERE PRODUCT_ID = ?", new ReflectionBeanRowMapper<ProductStock>(ProductStock.class), productId);
		if (l.size() != 1) {
			throw new ProductStockNotFoundException("No such product. Id: " + productId);
		}
		return l.get(0);
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.execute("DELETE FROM ProductStock");
	}

	@Override
	public void insert(ProductStock productStock) {
		jdbcInsert.execute(new BeanPropertySqlParameterSource(productStock));
	}

	@Override
	public void update(ProductStock bean) {
		jdbcTemplate.update("UPDATE ProductStock SET QUANTITY = ? WHERE PRODUCT_ID = ?", bean.getQuantity(), bean.getProductId());
	}
}
