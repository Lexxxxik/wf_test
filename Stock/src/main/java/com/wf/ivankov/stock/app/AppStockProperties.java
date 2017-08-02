package com.wf.ivankov.stock.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ivankov_A
 *
 */
@Component
@ConfigurationProperties("stock")
public class AppStockProperties {

	private boolean isProduction;

	public boolean isProduction() {
		return isProduction;
	}

	public void setProduction(boolean isProduction) {
		this.isProduction = isProduction;
	}

}
