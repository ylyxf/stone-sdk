package com.siqisoft.stone.admin.role.service;

import org.siqisource.stone.permission.annotation.Permission;
import org.siqisource.stone.permission.annotation.PermissionClass;
import org.siqisource.stone.web.constants.JspConstants;
import org.springframework.stereotype.Component;

@JspConstants
@Component
@PermissionClass("角色管理")
public class RolePerms {

	private static final String ENTITY = "role";

	@Permission("分配用户")
	public static final String SYNC_USER = ENTITY + ":assignUser";

}
