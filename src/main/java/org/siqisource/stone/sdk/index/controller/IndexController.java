package org.siqisource.stone.sdk.index.controller;

import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	ProjectService service;

	@RequestMapping("/index/Index.do")
	public String list(Project project, Model model) {
		return "index/Index";
	}

}
