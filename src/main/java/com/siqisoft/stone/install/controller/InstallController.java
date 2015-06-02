package com.siqisoft.stone.install.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.DownLoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siqisoft.stone.install.modle.DatabaseConnection;
import com.siqisoft.stone.install.service.DatabaseInstallService;
import com.siqisoft.stone.install.service.ProperitiesService;

@Controller
public class InstallController {

	@Autowired
	DatabaseInstallService databaseInstallService;

	@Autowired
	ProperitiesService properitiesService;

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
			databaseInstallService.checkConnection(databaseConnection);
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("install.databaseConnection",
					databaseConnection);
			return "install/ToInitDatabase";
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
			if ("true".equals(databaseInstallService.getInstalled())) {
				throw new BusinessException("stone已安装，请勿再次初始化数据库");
			}

			Session session = SecurityUtils.getSubject().getSession();
			DatabaseConnection databaseConnection = (DatabaseConnection) session
					.getAttribute("install.databaseConnection");

			databaseInstallService.runSql(databaseConnection);
			properitiesService.setConfig(databaseConnection);
		} catch (Exception e) {
			model.addAttribute("errmsg", e.getMessage());
			return "/install/ToInitDatabase";
		}
		return "install/ToSetAdminPassword";
	}

	@RequestMapping("/install/SetAdminPassword.do")
	public String setAdminPassword(String password, Model model) {
		try {
			// 判断是否已安装
			if ("true".equals(databaseInstallService.getInstalled())) {
				throw new BusinessException("stone已安装，请勿再次设置管理员密码");
			}

			Session session = SecurityUtils.getSubject().getSession();
			DatabaseConnection databaseConnection = (DatabaseConnection) session
					.getAttribute("install.databaseConnection");
			databaseInstallService.setAdminPassword(databaseConnection,
					password);
		} catch (Exception e) {
			model.addAttribute("errmsg", e.getMessage());
			return "/install/ToSetAdminPassword";
		}

		return "install/Success";
	}

	@RequestMapping("/install/saveProperities.do")
	public String saveProperities() throws IOException {
		Session session = SecurityUtils.getSubject().getSession();
		DatabaseConnection databaseConnection = (DatabaseConnection) session
				.getAttribute("install.databaseConnection");
		properitiesService.setConfig(databaseConnection);
		return "install/Success";
	}

	@RequestMapping("/install/downloadInstallSql.do")
	public ResponseEntity<byte[]> downloadInstallSql() throws IOException {
		Session session = SecurityUtils.getSubject().getSession();
		DatabaseConnection databaseConnection = (DatabaseConnection) session
				.getAttribute("install.databaseConnection");
		String databaseType = databaseConnection.getDbType();
		String destPath = databaseInstallService.zipInstallSql(databaseType);
		HttpHeaders headers = DownLoadUtil
				.prepareDownloadHeaders("sql-install.zip");
		return new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(new File(destPath)), headers,
				HttpStatus.CREATED);
	}

}
