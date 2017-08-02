/**
 * 
 */
package com.wf.ivankov.stock.fwk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.wf.ivankov.common.annotation.CountEnchancer;

/**
 * @author Ivankov_A
 *
 */
public class CountEnchancerAnnotationMethodResolver implements HandlerMethodArgumentResolver {

	private static final Logger LOG = LoggerFactory.getLogger(CountEnchancerAnnotationMethodResolver.class);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		LOG.debug("CountEnchancerAnnotationMethodResolver supportsParameter has: {}", parameter.hasParameterAnnotation(CountEnchancer.class));
		return parameter.hasParameterAnnotation(CountEnchancer.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		LOG.debug("CountEnchancerAnnotationMethodResolver resolveArgument");
		return mavContainer.getModel();
	}

}