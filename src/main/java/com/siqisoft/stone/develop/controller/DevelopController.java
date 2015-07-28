package com.siqisoft.stone.develop.controller;

import java.util.Map;

import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.ui.easyui.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.develop.model.GenerateCodeParams;
import com.siqisoft.stone.develop.model.Project;
import com.siqisoft.stone.develop.model.Table;
import com.siqisoft.stone.develop.service.ArchetypeService;
import com.siqisoft.stone.develop.service.CodeService;
import com.siqisoft.stone.develop.service.DatabaseService;

@Controller
public class DevelopController {

	@Autowired
	Project project;

	@Autowired
	ArchetypeService archetypeService;

	@Autowired
	CodeService codeService;

	@Autowired
	DatabaseService databaseService;

	@RequestMapping("/develop/Index.do")
	public String index(Model model) {
		// 准备好模板
		model.addAttribute("codeFileNameList", codeService.readCodeFileNameList());
		return "develop/TableList";
	}

	@RequestMapping("/develop/initProject.do")
	@ResponseBody
	public AjaxResponse initProject() {
		archetypeService.initProject();
		return new AjaxResponse("操作成功");
	}

	@RequestMapping("/develop/tableListData.do")
	@ResponseBody
	public Map<String, Object> tableListData(Table table, Paging paging) {
		String name = table.getName();
		return databaseService.listTable(paging, name);
	}

	@RequestMapping("/develop/generateCode.do")
	@ResponseBody
	public AjaxResponse generateCode(GenerateCodeParams generateCodeParams) {
		codeService.generateCode(generateCodeParams);
		return new AjaxResponse("操作成功");
	}

}
