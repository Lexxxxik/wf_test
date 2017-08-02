package com.wf.ivankov.shop.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ivankov_A
 *
 */
@Component
@ConfigurationProperties("shop")
public class AppShopProperties {

	private boolean isProduction;
	private Rest rest;

	public boolean isProduction() {
		return isProduction;
	}

	public void setProduction(boolean isProduction) {
		this.isProduction = isProduction;
	}

	public Rest getRest() {
		return rest;
	}

	public void setRest(Rest rest) {
		this.rest = rest;
	}

	public static class Rest {
		private String stockUrl;

		public String getStockUrl() {
			return stockUrl;
		}

		public void setStockUrl(String stockUrl) {
			this.stockUrl = stockUrl;
		}
	}

	public String getProductServiceBeanName() {
		// TODO Auto-generated method stub
		return null;
	}
}
