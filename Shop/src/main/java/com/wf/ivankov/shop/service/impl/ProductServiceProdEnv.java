package com.wf.ivankov.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wf.ivankov.common.annotation.PriceCorrection;
import com.wf.ivankov.shop.domain.Product;
import com.wf.ivankov.shop.jpa.ProductRepository;
import com.wf.ivankov.shop.service.ProductService;

/**
 * @author Ivankov_A
 *
 */
@Service
@Lazy
@Qualifier("prodProductService")
public class ProductServiceProdEnv implements ProductService {

	@Autowired
	private ProductRepository prRepository;
	
	@Override
	public List<Product> findAll() {
		Iterable<Product> source = prRepository.findAll();
		List<Product> target = new ArrayList<>();
		source.iterator().forEachRemaining(target::add);
		return target;
	}
	
	@Override
	@PriceCorrection
	public Product getOne(int id) {
		return prRepository.findOne(id);
	}

	@Override
	public Product save(Product bean) {
		return prRepository.save(bean);
	}

	@Override
	public void saveAll(List<Product> beans) {
		beans.forEach(prRepository::save);
	}

	@Override
	public void delete(int id) {
		prRepository.delete(prRepository.findOne(id));
	}

	@Override
	@PriceCorrection
	public List<Product> searchByName(String name) {
		return prRepository.findByName(name);
	}

}
