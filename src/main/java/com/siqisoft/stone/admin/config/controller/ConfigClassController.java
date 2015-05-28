package com.siqisoft.stone.admin.config.controller;

import java.util.List;

import org.siqisource.stone.config.model.ConfigClassEntity;
import org.siqisource.stone.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfigClassController {

	@Autowired
	ConfigService service;

	@RequestMapping("/config/configClass/List.do")
	public String list(Model model) {
		List<ConfigClassEntity> configClassList = service.listConfigClassEntity();
		model.addAttribute("configClassList", configClassList);
		return "config/ConfigClassList";
	}

}
