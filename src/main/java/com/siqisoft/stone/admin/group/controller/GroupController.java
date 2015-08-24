package com.siqisoft.stone.admin.group.controller;

import java.util.List;

import org.siqisource.stone.group.model.Group;
import org.siqisource.stone.group.service.GroupService;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.ui.Notify;
import org.siqisource.stone.ui.easyui.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.admin.group.service.GroupConditionBuilder;

@Controller
public class GroupController {

	@Autowired
	GroupService service;

	@RequestMapping("/group/GroupMain.do")
	public String index(Model model) {
		return "/group/GroupMain";
	}

	@RequestMapping("/group/groupTreeData.do")
	@ResponseBody
	public List<TreeNode> listData(GroupQueryForm groupQueryForm) {
		Condition condition = GroupConditionBuilder
				.listCondition(groupQueryForm);
		List<TreeNode> treeNodeList = service.listTreeNode(condition);
		return treeNodeList;
	}

	@RequestMapping("/group/GroupRead.do")
	public String read(Integer id, Model model) {
		Group group = service.read(id);
		model.addAttribute("group", group);
		return "group/GroupRead";
	}
	
	@RequestMapping("/group/GroupAddInit.do")
    public String addInit(Integer id, Model model) {
		Group group = service.read(id);
        model.addAttribute("group", group);
        return "group/GroupAdd";
    }
    
    @RequestMapping("/group/groupAdd.do")
    @ResponseBody
    public AjaxResponse add(Group group, Model model) {
        service.insert(group);
        return new Notify("新增成功",service.toTreeNode(group));
    }
    
    @RequestMapping("/group/groupDelete.do")
    @ResponseBody
    public AjaxResponse delete(Integer id, Model model) {
    	Group group = this.service.read(id);
        service.logicDelete(id);
        return new Notify("删除成功",service.toTreeNode(group));
    }
    
    @RequestMapping("/group/GroupEditInit.do")
    public String editInit(Integer id, Model model) {
        Group group = service.read(id);
        model.addAttribute("group", group);
        return "group/GroupEdit";
    }
    
    @RequestMapping("/group/groupEdit.do")
    @ResponseBody
    public AjaxResponse edit(Group group, Model model) {
        service.update(group);
        return  new Notify("编辑成功",service.toTreeNode(group));
    }

}
