package com.siqisoft.stone.develop.controller;

import java.util.Map;

import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.ui.bt.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.develop.model.Project;
import com.siqisoft.stone.develop.service.ArchetypeService;
import com.siqisoft.stone.develop.service.DatabaseService;

@Controller
public class DevelopController {

	@Autowired
	Project project;

	@Autowired
	ArchetypeService archetypeService;

	@Autowired
	DatabaseService databaseService;

	@RequestMapping("/develop/Index.do")
	public String index() {
		return "develop/Index";
	}

	@RequestMapping("/develop/tableListData.do")
	@ResponseBody
	public Map<String,Object>  tableListData(Paging paging) {
		return databaseService.listTable(paging);
	}

	@RequestMapping("/develop/initProject.do")
	@ResponseBody
	public AjaxResponse initProject() {
		// config
		archetypeService.initProject();
		return new AjaxResponse("操作成功");
	}

}
