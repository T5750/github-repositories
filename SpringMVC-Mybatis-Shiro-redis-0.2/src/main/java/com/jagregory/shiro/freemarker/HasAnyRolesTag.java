package com.jagregory.shiro.freemarker;

import org.apache.shiro.subject.Subject;

public class HasAnyRolesTag extends RoleTag {
	private static final String ROLE_NAMES_DELIMETER = ",";

	protected boolean showTagBody(String roleNames) {
		boolean hasAnyRole = false;
		Subject subject = getSubject();
		if (subject != null) {
			for (String role : roleNames.split(",")) {
				if (subject.hasRole(role.trim())) {
					hasAnyRole = true;
					break;
				}
			}
		}
		return hasAnyRole;
	}
}
