package com.wf.ivankov.shop.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.wf.ivankov.shop.domain.Product;

/**
 * @author Ivankov_A
 *
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	List<Product> findByName(@Param("name") String name);

}
