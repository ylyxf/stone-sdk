package com.siqisoft.stone.admin.role.controller;

import java.util.List;

import org.siqisource.stone.permission.model.PermissionClassEntity;
import org.siqisource.stone.role.model.Role;
import org.siqisource.stone.role.service.RoleOperationService;
import org.siqisource.stone.role.service.RoleService;
import org.siqisource.stone.ui.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleOperationController {

	@Autowired
	RoleOperationService service;
	
	@Autowired
	RoleService roleService;

	@RequestMapping("/role/RoleOperationList.do")
	public String list(Integer id, Model model) {
		List<PermissionClassEntity> permissionClassEntityList = service
				.getPermissionEntityList(id);
		model.addAttribute("permissionClassEntityList",
				permissionClassEntityList);
		Role role = roleService.read(id);
		model.addAttribute("role", role);
		return "role/RoleOperationList";
	}
	
	@RequestMapping("/role/addOperationToRole.do")
	@ResponseBody
	public AjaxResponse addOperationToRole(Integer roleId,String operationCode, Model model) {
		this.service.addOperationToRole(roleId, operationCode);
		return  new AjaxResponse("设置成功");
	}
	
	@RequestMapping("/role/removeOperationFromRole.do")
	@ResponseBody
	public AjaxResponse removeOperationFromRole(Integer roleId,String operationCode, Model model) {
		this.service.removeOperationFromRole(roleId, operationCode);
		return  new AjaxResponse("取消成功");
	}
	
	
}
