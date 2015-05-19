package com.siqisoft.stone.admin.config.controller;

import java.util.List;

import org.siqisource.stone.config.model.ConfigClass;
import org.siqisource.stone.config.service.ConfigClassService;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfigClassController {

	@Autowired
	ConfigClassService service;

	@RequestMapping("/config/configClass/List.do")
	public String list(Model model) {
		SimpleCondition condition = new SimpleCondition();
		condition.orderAsc("sortNo");
		List<ConfigClass> configClassList = service.list(condition);
		model.addAttribute("configClassList", configClassList);
		return "config/ConfigClassList";
	}

}
