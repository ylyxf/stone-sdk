package com.siqisoft.stone.develop.controller;

import org.siqisource.stone.ui.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.develop.model.Project;
import com.siqisoft.stone.develop.service.ArchetypeService;

@Controller
public class DevelopController {

	@Autowired
	Project project;

	@Autowired
	ArchetypeService service;

	@RequestMapping("/develop/Index.do")
	public String index() {
		return "develop/Index";
	}

	@RequestMapping("/develop/initProject.do")
	@ResponseBody
	public AjaxResponse initProject() {
		// config
		service.initProject();
		return new AjaxResponse("操作成功");
	}

}
