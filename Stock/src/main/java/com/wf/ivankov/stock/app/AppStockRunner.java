package com.wf.ivankov.stock.app;

import org.springframework.boot.SpringApplication;

/**
 * @author Ivankov_A
 *
 */
public class AppStockRunner {

	public static void main(String[] args) {
		//Object[] sources = { AppStockConfig.class, AppStockConfigurerAdapter.class };
		SpringApplication.run(AppStockConfig.class, args);
	}
}
