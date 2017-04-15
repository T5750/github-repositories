package com.jagregory.shiro.freemarker;

public class LacksPermissionTag extends PermissionTag {
	protected boolean showTagBody(String p) {
		return !isPermitted(p);
	}
}
