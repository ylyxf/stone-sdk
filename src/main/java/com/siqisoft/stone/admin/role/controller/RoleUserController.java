package com.siqisoft.stone.admin.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.role.model.Role;
import org.siqisource.stone.role.model.RoleUserView;
import org.siqisource.stone.role.service.RoleService;
import org.siqisource.stone.role.service.RoleUserService;
import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.ui.easyui.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.admin.role.service.RoleUserAdminService;

@Controller
public class RoleUserController {

	@Autowired
	RoleUserService service;

	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleUserAdminService roleUserAdminService;

	@RequestMapping("/role/RoleUserList.do")
	public String list(Integer id, Model model) {
		Role role = roleService.read(id);
		model.addAttribute("role", role);
		return "role/RoleUserList";
	}

	@RequestMapping("/role/roleUserListData.do")
	@ResponseBody
	public Map<String, Object> listData(Integer roleId, Paging paging) {
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("ru.roleId", roleId);
		int count = service.countRoleUser(condition);
		List<RoleUserView> roleUserList = service.listRoleUser(condition,
				paging.getRowBounds());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", count);
		result.put("rows", roleUserList);
		return result;
	}

	@RequestMapping("/role/addUserToRole.do")
	@ResponseBody
	public AjaxResponse addUserToRole(Integer roleId, String[] groupUserCode) {
		if(groupUserCode != null){
			roleUserAdminService.addUserToRole(roleId, groupUserCode);
		}
		return new AjaxResponse("关联成功");
	}

	@RequestMapping("/role/removeUserFromRole.do")
	@ResponseBody
	public AjaxResponse removeUserFromRole( Integer[] id,
			String operationCode) {
		SimpleCondition condition = new SimpleCondition();
		condition.andIn("id", id);
		this.service.deleteBatch(condition);
		return new AjaxResponse("移除成功");
	}

}
