package com.jagregory.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class GuestTag extends SecureTag {
	private static final Logger log = Logger.getLogger("AuthenticatedTag");

	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		if ((getSubject() == null) || (getSubject().getPrincipal() == null)) {
			if (log.isDebugEnabled()) {
				log.debug(
						"Subject does not exist or does not have a known identity (aka 'principal').  Tag body will be evaluated.");
			}
			renderBody(env, body);
		} else if (log.isDebugEnabled()) {
			log.debug(
					"Subject exists or has a known identity (aka 'principal').  Tag body will not be evaluated.");
		}
	}
}
