package com.siqisoft.stone.admin.role.controller;

import java.util.List;

import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.role.model.Role;
import org.siqisource.stone.role.service.RoleService;
import org.siqisource.stone.ui.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.admin.role.service.RoleConditionBuilder;



@Controller
public class RoleController {

	@Autowired
	RoleService service;

	@RequestMapping("/role/RoleList.do")
	public String list(Model model) {
		return "role/RoleList";
	}

	@RequestMapping("/role/roleListData.do")
	@ResponseBody
	public List<Role> listData(RoleQueryForm roleQueryForm) {
		Condition condition = RoleConditionBuilder.listCondition(roleQueryForm);
		List<Role> roleList = service.list(condition);
		return roleList;
	}

	@RequestMapping("/role/RoleRead.do")
	public String read(Integer id, Model model) {
		Role role = service.read(id);
		model.addAttribute("role", role);
		return "role/RoleRead";
	}

	@RequestMapping("/role/RoleAddInit.do")
	public String addInit(Role role, Model model) {
		return "role/RoleAdd";
	}

	@RequestMapping("/role/RoleAdd.do")
	public String add(Role role, Model model) {
		service.insert(role);
		return this.read(role.getId(), model);
	}

	@RequestMapping("/role/roleDelete.do")
	@ResponseBody
	public AjaxResponse delete(Integer[] id, Model model) {
		// 判断是否被关联
		if(id!=null){
			service.deleteBatch(id);
			return new AjaxResponse("删除成功",id.length) ;
		}
		return new AjaxResponse("未删除任何数据") ;
	}

	@RequestMapping("/role/RoleEditInit.do")
	public String editInit(Integer id, Model model) {
		Role role = service.read(id);
		model.addAttribute("role", role);
		return "role/RoleEdit";
	}

	@RequestMapping("/role/RoleEdit.do")
	public String edit(Role role, Model model) {
		service.update(role);
		return this.read(role.getId(), model);
	}


}
