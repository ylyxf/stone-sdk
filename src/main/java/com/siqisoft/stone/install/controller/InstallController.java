package com.siqisoft.stone.install.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.siqisource.stone.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siqisoft.stone.install.modle.DatabaseConnection;
import com.siqisoft.stone.install.service.InstallService;

@Controller
public class InstallController {

	@Autowired
	InstallService service;

	@RequestMapping("/install/dataSourceInfo.do")
	public String dataSourceInfo(String dataSource, Model model) {
		if ("containerDataSource".equals(dataSource)) {
			return "install/ContainerDataSource";
		}
		if ("innerDataSource".equals(dataSource)) {
			return "install/InnerDataSource";
		}
		return "";
	}

	@RequestMapping("/install/checkDataSource.do")
	public String checkDataSource(DatabaseConnection databaseConnection,
			Model model) {
		try {
			service.checkConnection(databaseConnection);
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("install.databaseConnection",
					databaseConnection);
			return "install/InitDatabase";
		} catch (Exception e) {
			model.addAttribute("databaseConnection", databaseConnection);
			model.addAttribute("errmsg", e.getMessage());
			return dataSourceInfo(databaseConnection.getDataSource(), model);
		}
	}

	@RequestMapping("/install/InitDatabase.do")
	public String initDatabase(Model model) {
		try {
			// 判断是否已安装
			if ("true".equals(service.getInstalled())) {
				throw new BusinessException("stone已安装，请勿再次初始化数据库");
			}

			Session session = SecurityUtils.getSubject().getSession();
			DatabaseConnection databaseConnection = (DatabaseConnection) session
					.getAttribute("install.databaseConnection");

			service.initDatabase(databaseConnection);
			service.setConfig(databaseConnection);

		} catch (Exception e) {
			model.addAttribute("errmsg", e.getMessage());
			return "/install/InitDatabase";
		}

		return "install/SetAdminPassword";
	}

	@RequestMapping("/install/SetAdminPassword.do")
	public String setAdminPassword(String password, Model model) {
		try {
			// 判断是否已安装
			if ("true".equals(service.getInstalled())) {
				throw new BusinessException("stone已安装，请勿再次设置管理员密码");
			}

			Session session = SecurityUtils.getSubject().getSession();
			DatabaseConnection databaseConnection = (DatabaseConnection) session
					.getAttribute("install.databaseConnection");

			service.setAdminPassword(databaseConnection, password);
			service.setConfig(databaseConnection);

		} catch (Exception e) {
			model.addAttribute("errmsg", e.getMessage());
			return "/install/SetAdminPassword";
		}

		return "install/Success";
	}

}
