package com.jagregory.shiro.freemarker;

public class HasRoleTag extends RoleTag {
	protected boolean showTagBody(String roleName) {
		return (getSubject() != null) && (getSubject().hasRole(roleName));
	}
}
