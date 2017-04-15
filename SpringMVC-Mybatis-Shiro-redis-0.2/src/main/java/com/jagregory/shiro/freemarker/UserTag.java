package com.jagregory.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class UserTag extends SecureTag {
	static final Logger log = Logger.getLogger("UserTag");

	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		if ((getSubject() != null) && (getSubject().getPrincipal() != null)) {
			log.debug(
					"Subject has known identity (aka 'principal'). Tag body will be evaluated.");
			renderBody(env, body);
		} else {
			log.debug(
					"Subject does not exist or have a known identity (aka 'principal'). Tag body will not be evaluated.");
		}
	}
}
