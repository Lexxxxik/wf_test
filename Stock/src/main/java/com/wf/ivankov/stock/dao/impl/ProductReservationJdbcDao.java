package com.wf.ivankov.stock.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.wf.ivankov.common.domain.ProductReservation;
import com.wf.ivankov.stock.dao.ProductReservationDao;
import com.wf.ivankov.stock.dao.ReflectionBeanRowMapper;

/**
 * @author Ivankov_A
 *
 */
@Repository
public class ProductReservationJdbcDao implements ProductReservationDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ProductReservation");
	}

	@Override
	public List<ProductReservation> findAll() {
		return jdbcTemplate.query("SELECT * FROM ProductReservation", new ReflectionBeanRowMapper<ProductReservation>(ProductReservation.class));
	}

	@Override
	public void insert(ProductReservation productReservation) {
		jdbcInsert.execute(new BeanPropertySqlParameterSource(productReservation));
	}
}
