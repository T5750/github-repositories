package com.jagregory.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public abstract class RoleTag extends SecureTag {
	String getName(Map params) {
		return getParam(params, "name");
	}

	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		boolean show = showTagBody(getName(params));
		if (show) {
			renderBody(env, body);
		}
	}

	protected abstract boolean showTagBody(String paramString);
}
