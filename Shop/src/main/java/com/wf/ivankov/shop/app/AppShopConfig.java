package com.wf.ivankov.shop.app;

import java.math.BigDecimal;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.wf.ivankov.shop.domain.Product;
import com.wf.ivankov.shop.jpa.ProductRepository;

/**
 * @author Ivankov_A
 *
 */
@SpringBootApplication
@ComponentScan("com.wf.ivankov.shop")
@EnableJpaRepositories("com.wf.ivankov.shop.jpa")
@EntityScan("com.wf.ivankov.shop.domain")
@EnableAspectJAutoProxy
public class AppShopConfig {

	private static final Logger LOG = LoggerFactory.getLogger(AppShopConfig.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Void demo(ProductRepository repository, AppShopProperties props) {
		LOG.info("Products found with findAll():");
		LOG.info("-------------------------------");
		for (Product Product : repository.findAll()) {
			LOG.info(Product.toString());
		}
		LOG.info("");
		return null;
	}

	@Bean
	@Scope("prototype")
	public Product product() {
		int id = new Random().nextInt(100);
		Product result = new Product(id, "GeneratedProduct" + id, new BigDecimal("" + id * id));
		return result;
	}
}
