package com.siqisoft.stone.develop.controller;

import org.siqisource.stone.ui.AjaxResponse;
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
		databaseService.listTable();
		return "develop/Index";
	}

	@RequestMapping("/develop/initProject.do")
	@ResponseBody
	public AjaxResponse initProject() {
		// config
		archetypeService.initProject();
		return new AjaxResponse("操作成功");
	}

}
