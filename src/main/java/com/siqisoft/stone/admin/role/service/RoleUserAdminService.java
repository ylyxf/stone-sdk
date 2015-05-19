package com.siqisoft.stone.admin.role.service;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.role.model.RoleUser;
import org.siqisource.stone.role.service.RoleUserService;
import org.siqisource.stone.user.model.GroupUser;
import org.siqisource.stone.user.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleUserAdminService {

	@Autowired
	private RoleUserService service;
	
	@Autowired
	private GroupUserService groupUserService;

	@Transactional
	public void addUserToRole(Integer roleId, String[] groupUserCode) {
		List<Integer> userList = new ArrayList<Integer>();
		for (String groupUser : groupUserCode) {
			if (groupUser.indexOf('u') != -1) {
				userList.add(Integer.parseInt(groupUser.substring(2)));
			} else {
				SimpleCondition condition = new SimpleCondition();
				condition.andEqual("groupId",
						Integer.parseInt(groupUser.substring(2)));
				List<GroupUser> groupUserList = groupUserService.list(condition);
				for (GroupUser currGroupUser : groupUserList) {
					userList.add(currGroupUser.getUserId());
				}
			}
		}

		for (Integer userId : userList) {
			SimpleCondition condition = new SimpleCondition();
			condition.andEqual("roleId", roleId);
			condition.andEqual("userId", userId);
			if (service.count(condition) == 0) {
				RoleUser roleUser = new RoleUser();
				roleUser.setRoleId(roleId);
				roleUser.setUserId(userId);
				service.insert(roleUser);
			}
		}
	}

}
