package com.wf.ivankov.stock.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wf.ivankov.common.domain.ProductStock;

/**
 * @author Ivankov_A
 *
 */
@Component
public class ProductStockBeanValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return ProductStock.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", null, "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", null, "field.required");

		ProductStock theBean = (ProductStock) target;
		if (theBean.getQuantity() <= 0) {
			errors.rejectValue("quantity", null, "Can't be <= 0");
		}
	}

}
