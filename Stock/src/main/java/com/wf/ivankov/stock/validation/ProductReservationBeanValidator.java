package com.wf.ivankov.stock.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wf.ivankov.common.domain.ProductReservation;

/**
 * @author Ivankov_A
 *
 */
@Component
public class ProductReservationBeanValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return ProductReservation.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", null, "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", null, "field.required");

		ProductReservation theBean = (ProductReservation) target;
		if (theBean.getQuantity() <= 0) {
			errors.rejectValue("quantity", null, "Can't be <= 0");
		}
	}

}
