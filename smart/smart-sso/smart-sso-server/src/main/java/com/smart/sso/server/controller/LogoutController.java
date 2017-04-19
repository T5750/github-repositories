package com.smart.sso.server.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.mvc.util.CookieUtils;
import com.smart.mvc.util.StringUtils;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.server.common.TokenManager;

/**
 * 远程登出
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
	@Resource
	private TokenManager tokenManager;

	@RequestMapping(method = RequestMethod.GET)
	public String logout(@ValidateParam(name = "返回链接") String backUrl,
			HttpServletRequest request) {
		String token = CookieUtils.getCookie(request, "token");
		if (StringUtils.isNotBlank(token)) {
			tokenManager.remove(token);
		}
		request.getSession().invalidate();
		return "redirect:"
				+ (StringUtils.isBlank(backUrl) ? "/admin/admin" : backUrl);
	}
}