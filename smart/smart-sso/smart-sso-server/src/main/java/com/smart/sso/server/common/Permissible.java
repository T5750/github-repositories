package com.smart.sso.server.common;

import java.lang.annotation.*;

/**
 * 权限修改方法注解
 * 
 * 注：方法添加这个注解的时候，方法的第一个参数一定为appId
 * 
 * 如：public void enable(Integer appId, Boolean isEnable, List<Integer> idList);
 * 
 * @author Joe
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Permissible {
}