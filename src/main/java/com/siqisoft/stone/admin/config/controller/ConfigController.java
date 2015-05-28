package com.siqisoft.stone.admin.config.controller;

import java.util.List;

import org.siqisource.stone.config.model.ConfigClassEntity;
import org.siqisource.stone.config.model.ConfigEntity;
import org.siqisource.stone.config.service.ConfigService;
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

	@RequestMapping("/config/ConfigRead.do")
	public String read(String classCode, Model model) {
		ConfigClassEntity configClassEntity = this.service
				.readConfigClassEntity(classCode);
		List<ConfigEntity> configConfigList = configClassEntity
				.getConfigEntityList();

		model.addAttribute("configClass", configClassEntity);
		model.addAttribute("configList", configConfigList);

		return "config/ConfigRead";
	}

	@RequestMapping("/config/ConfigEditInit.do")
	public String editInit(String classCode, Model model) {
		ConfigClassEntity configClassEntity = this.service
				.readConfigClassEntity(classCode);
		List<ConfigEntity> configConfigList = configClassEntity
				.getConfigEntityList();

		model.addAttribute("configClass", configClassEntity);
		model.addAttribute("configList", configConfigList);

		return "config/ConfigEdit";
	}

	@RequestMapping("/config/ConfigEdit.do")
	@ResponseBody
	public AjaxResponse edit(ConfigQueryForm configQueryForm, Model model) {
		ConfigClassEntity configClassEntity = new ConfigClassEntity();
		configClassEntity.setCode(configQueryForm.getClassCode());
		configClassEntity.setConfigEntityList(configQueryForm.getConfigList());
		return new AjaxResponse("保存成功", configQueryForm.getClassCode());
	}

}
