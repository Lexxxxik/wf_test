package com.wf.ivankov.shop.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wf.ivankov.shop.domain.Product;
import com.wf.ivankov.shop.fwk.UnrealProductFactory;
import com.wf.ivankov.shop.service.ProductService;

/**
 * @author Ivankov_A
 *
 */
@Service
@Lazy
@Qualifier("testProductService")
public class ProductServiceTestEnv implements ProductService {
	
	private List<Product> repo;
	
	@Autowired
	private UnrealProductFactory prodFactory;

	@PostConstruct
	private void init() {
		repo = new ArrayList<>();
		repo.add(new Product(1, "Каша", new BigDecimal("1.22")));
		repo.add(new Product(2, "Молоко", new BigDecimal("2.41")));
		repo.add(new Product(3, "Мороженое", new BigDecimal("4.22")));
		repo.add(prodFactory.getProduct());
		repo.add(prodFactory.getProduct());
		repo.add(prodFactory.getProduct());
	}

	@Override
	public Product getOne(int id) {
		return repo.stream().filter(p -> p.getId() == id).findFirst().get();
	}

	@Override
	public Product save(Product bean) {
		if (bean.getId() == null) {
			Integer nextId = repo.stream().map(Product::getId).max(Integer::max).get();
			bean.setId(nextId);
			repo.add(bean);
		}
		return bean;
	}

	@Override
	public void saveAll(List<Product> beans) {
		beans.forEach(this::save);
	}

	@Override
	public void delete(int id) {
		repo.stream().filter(p -> p.getId() == id).findFirst().ifPresent(p -> repo.remove(p));
	}

	@Override
	public List<Product> searchByName(String name) {
		return repo.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<Product> findAll() {
		return Collections.unmodifiableList(repo);
	}

}
