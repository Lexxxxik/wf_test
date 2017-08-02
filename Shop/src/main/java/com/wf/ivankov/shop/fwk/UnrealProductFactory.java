package com.wf.ivankov.shop.fwk;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import com.wf.ivankov.shop.domain.Product;

/**
 * @author Ivankov_A
 *
 */
@Component
public interface UnrealProductFactory {

	@Lookup
	Product getProduct();
}
