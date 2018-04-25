package com.giit.www.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.giit.www.system.service.UserBiz;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Controller
@RequestMapping("account.do")
public class AccountController {
	@Resource(name = "userBizImpl")
	private UserBiz userBiz;

	/**
	 * 个人信息-新密码
	 */
	@RequiresAuthentication
	@RequestMapping("profile.view")
	public String profileView() {
		return "/admin/system/account/profile";
	}

	/**
	 * 个人信息-更新密码
	 */
	@RequiresAuthentication
	@RequestMapping("update")
	public String update(HttpSession session, String password) {
		String id = (String) session.getAttribute("username");
		userBiz.updatePassword(id, password);
		return "redirect:/account.do/profile.view";
	}
}
