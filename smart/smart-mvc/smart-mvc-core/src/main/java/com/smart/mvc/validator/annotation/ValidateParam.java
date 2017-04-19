package com.smart.mvc.validator.annotation;

import java.lang.annotation.*;

import com.smart.mvc.validator.Validator;

/**
 * 自定义请求参数注解
 * 
 * @author Joe
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateParam {
	/**
	 * 验证器
	 * 
	 * @return
	 */
	Validator[] validators() default {};

	/**
	 * 参数的描述名称
	 * 
	 * @return
	 */
	String name() default "";
}
