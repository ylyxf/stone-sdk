package com.siqisoft.stone.admin.config.controller;

import java.util.List;

import org.siqisource.stone.config.model.Config;
import org.siqisource.stone.config.model.ConfigClass;
import org.siqisource.stone.config.service.ConfigClassService;
import org.siqisource.stone.config.service.ConfigService;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.ui.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfigController {

	@Autowired
	ConfigService service;

	@Autowired
	ConfigClassService configClassService;

	@RequestMapping("/config/ConfigRead.do")
	public String read(String classCode, Model model) {
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("classCode", classCode);
		condition.orderAsc("sortNo");
		List<Config> configList = this.service.list(condition);

		ConfigClass configClass = configClassService.read(classCode);
		model.addAttribute("configList", configList);
		model.addAttribute("configClass", configClass);
		return "config/ConfigRead";
	}

	@RequestMapping("/config/ConfigEditInit.do")
	public String editInit( String classCode,
			Model model) {
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("classCode", classCode);
		condition.orderAsc("sortNo");
		List<Config> configList = this.service.list(condition);

		ConfigClass configClass = configClassService.read(classCode);
		model.addAttribute("configList", configList);
		model.addAttribute("configClass", configClass);
		return "config/ConfigEdit";
	}

	@RequestMapping("/config/ConfigEdit.do")
	@ResponseBody
	public AjaxResponse edit(ConfigQueryForm configQueryForm, Model model) {
		this.service.saveConfigList(configQueryForm.getConfigList());
		return new AjaxResponse("保存成功", configQueryForm.getClassCode());
	}

}
