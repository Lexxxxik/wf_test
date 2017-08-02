package com.wf.ivankov.shop.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.wf.ivankov.shop.app.AppShopConfig;
import com.wf.ivankov.shop.domain.Product;


/**
 * @author Ivankov_A
 *
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppShopConfig.class })
@DataJpaTest*/
public class ProductRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

//	@Test
	public void testFindByLastName() {
		Product customer = new Product(1, "Banana", new BigDecimal("999"));
		entityManager.persist(customer);

		List<Product> findByLastName = productRepository.findByName("Banana");

		assertThat(findByLastName).extracting(Product::getName).containsOnly(customer.getName());
	}
}