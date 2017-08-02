/**
 * 
 */
package com.wf.ivankov.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Ivankov_A
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface PriceCorrection {

}
