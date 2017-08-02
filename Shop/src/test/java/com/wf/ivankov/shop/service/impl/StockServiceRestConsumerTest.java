package com.wf.ivankov.shop.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.wf.ivankov.shop.app.AppShopConfig;

/**
 * @author Ivankov_A
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppShopConfig.class })
public class StockServiceRestConsumerTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}
}
