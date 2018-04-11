package com.xai.common.controller;

import org.springframework.stereotype.Controller;
import com.xai.common.utils.ShiroUtils;
import com.xai.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}