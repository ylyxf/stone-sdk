package com.siqisoft.stone.install.service;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.JsonUtil;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.install.modle.DatabaseConnection;

@Service
public class ProperitiesService {

	public void setConfig(DatabaseConnection databaseConnection) {

		try {

			PropertiesConfiguration config = new PropertiesConfiguration();
			PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(
					config);

			URL configFileURL = this.getClass().getClassLoader()
					.getResource("config.properties");
			layout.load(new InputStreamReader(configFileURL.openStream()));

			config.setProperty("install.installed", "true");

			config.setProperty("dataSource.databaseType",
					databaseConnection.getDbType());
			config.setProperty("dataSource.name",
					databaseConnection.getDataSource());
			config.setProperty("containerDataSource.jndi",
					databaseConnection.getJndiName());
			config.setProperty("innerDataSource.driver",
					databaseConnection.getDbDriver());
			config.setProperty("innerDataSource.url",
					databaseConnection.getDbUrl());
			config.setProperty("innerDataSource.username",
					databaseConnection.getDbUser());
			config.setProperty("innerDataSource.password",
					databaseConnection.getDbPassword());

			String databaseTypep = databaseConnection.getDbType();
			
			//Oracle没有boolean类型
			if ("Oracle".equals(databaseTypep)) {
				String authenticationQuery = String.valueOf(config
						.getProperty("security.authenticationQuery"));
				authenticationQuery = authenticationQuery.replaceAll("true",
						"'1'");
				authenticationQuery = authenticationQuery.replaceAll("false",
						"'0'");
				config.setProperty("security.authenticationQuery",
						authenticationQuery);
			}

			File outputFile = new File(configFileURL.toURI());
			layout.save(new FileWriter(outputFile));
		} catch (Exception e) {
			throw new BusinessException("设置配置文件时出错!请清空数据库后再次初始化:"
					+ JsonUtil.clearString(e.getMessage()));
		}
	}
}
