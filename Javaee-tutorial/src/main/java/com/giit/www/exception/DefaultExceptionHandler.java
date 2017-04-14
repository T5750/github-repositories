package com.giit.www.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-12
 * <p>
 * Version: 1.0
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, Exception e) {
		ModelAndView mv = new ModelAndView();
		if (e instanceof AuthorizationException) {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
}
