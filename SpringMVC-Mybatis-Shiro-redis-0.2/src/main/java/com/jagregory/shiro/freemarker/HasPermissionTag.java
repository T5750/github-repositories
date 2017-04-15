package com.jagregory.shiro.freemarker;

public class HasPermissionTag extends PermissionTag {
	protected boolean showTagBody(String p) {
		return isPermitted(p);
	}
}
