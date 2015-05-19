package com.siqisoft.stone.install.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siqisoft.stone.install.modle.DatabaseConnection;
import com.siqisoft.stone.install.service.InstallCheckService;
import com.siqisoft.stone.install.service.InstallService;

@Controller
public class InstallController {

	@Autowired
	InstallService service;

	@Autowired
	InstallCheckService checkService;

	@RequestMapping("/install/initDatabase.do")
	public String initDatabase(DatabaseConnection databaseConnection,
			Model model) {
		String success = "install/Success";
		//判断是否已安装
		if ("true".equals(checkService.getInstalled())) {
			return success;
		}
		
		model.addAttribute("databaseConnection", databaseConnection);
		try {
			service.initDatabase(databaseConnection);
		} catch (Exception e) {
			model.addAttribute("errmsg", e.getMessage());
			return "install/InstallMain";
		}
		service.setConfig(databaseConnection);
		return "install/Success";
	}

}
