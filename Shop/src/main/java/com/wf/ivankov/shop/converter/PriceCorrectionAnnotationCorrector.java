package com.wf.ivankov.shop.converter;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wf.ivankov.shop.domain.Product;

/**
 * @author Ivankov_A
 *
 */
@Component
public class PriceCorrectionAnnotationCorrector {

	private final Logger LOG = LoggerFactory.getLogger(PriceCorrectionAnnotationCorrector.class);

	public void correctPrice(Product product, String role) {
		int percentageModifier = 100;
		switch (role) {
		case "ROLE_ADMIN":
			percentageModifier = 80;
			break;
		case "ROLE_USER":
			percentageModifier = 130;
			break;
		case "ROLE_OPT":
			percentageModifier = 110;
			break;
		default:
			LOG.debug("No PriceCorrection policy for group: {}", role);
			break;
		}
		product.setCost(product.getCost().divide(new BigDecimal("100")).multiply(new BigDecimal(percentageModifier)));
	}

}
