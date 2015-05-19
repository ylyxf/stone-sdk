package com.siqisoft.stone.admin.user.controller;

import java.util.List;

import org.siqisource.stone.group.model.Group;
import org.siqisource.stone.group.service.GroupService;
import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.user.model.User;
import org.siqisource.stone.user.service.GroupUserService;
import org.siqisource.stone.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	GroupService groupService;

	@Autowired
	GroupUserService groupUserService;

	@RequestMapping("/user/UserMain.do")
	public String main(Model model) {
		return "user/UserMain";
	}

	@RequestMapping("/user/UserList.do")
	public String userList(Integer groupId, Model model) {

		Group group = groupService.read(groupId);
		model.addAttribute("group", group);

		return "user/UserList";
	}

	@RequestMapping("/user/userListData.do")
	@ResponseBody
	public List<User> userListData(Integer groupId, Model model) {
		SimpleCondition condition = new SimpleCondition();
		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		condition.orderAsc("sortNo");
		List<User> userList = groupUserService.listUserByGroupId(groupId,
				condition);

		return userList;
	}
	
	@RequestMapping("/user/UserRead.do")
	public String userRead(Integer id, Model model) {
		User user = service.readUser(id);
		model.addAttribute("user", user);
		return "user/UserRead";
	}

	@RequestMapping("/user/UserEditInit.do")
	public String userEditInit(Integer id, Model model) {
		User user = service.readUser(id);
		model.addAttribute("user", user);
		return "user/UserEdit";
	}

	@RequestMapping("/user/userEdit.do")
	@ResponseBody
	public AjaxResponse userEdit(User user, Integer defaultGroupId, Model model) {
		service.updateUser(user, defaultGroupId);
		return new AjaxResponse("更新成功",user);
	}
	
	@RequestMapping("/user/UserAddInit.do")
	public String userAddInit(Integer defaultGroupId, Model model) {
		model.addAttribute("defaultGroupId", defaultGroupId);
		return "user/UserAdd";
	}

	@RequestMapping("/user/userAdd.do")
	@ResponseBody
	public AjaxResponse userAdd(User user, Integer defaultGroupId, Model model) {
		service.addUser(user, defaultGroupId);
		return new AjaxResponse("新增成功",user);
	}
	
	@RequestMapping("/user/userDelete.do")
	@ResponseBody
	public AjaxResponse userDelete(Integer id,Integer currentGroupId, Model model) {
		this.service.deleteUser(id);
		return new AjaxResponse("删除成功",id);
	}
	
	@RequestMapping("/user/userRemove.do")
	@ResponseBody
	public AjaxResponse userRemove(Integer id,Integer currentGroupId, Model model) {
		this.service.removeUser(id, currentGroupId);
		return new AjaxResponse("移除成功",id); 
	}

}
