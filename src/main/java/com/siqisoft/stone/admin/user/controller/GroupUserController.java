package com.siqisoft.stone.admin.user.controller;

import java.util.List;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.ui.easyui.TreeNode;
import org.siqisource.stone.user.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GroupUserController {

	@Autowired
	GroupUserService groupUserService;

	@RequestMapping("/group/groupUser/groupUserTreeData.do")
	@ResponseBody
	public List<TreeNode> groupUserTreeData(String id) {
		id = id == null ? "g_0" : id;
		Integer parentId = Integer.parseInt(id.substring(2));
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("parentId", parentId);
		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		condition.orderAsc("sortNo");
		List<TreeNode> result = groupUserService.listGroupUserTreeNode(
				condition, parentId);
		return result;
	}

}
