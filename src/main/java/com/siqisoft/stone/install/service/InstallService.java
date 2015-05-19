package com.siqisoft.stone.install.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.JsonUtil;
import org.siqisource.stone.utils.OrderedProperties;
import org.siqisource.stone.web.Path;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.install.modle.DatabaseConnection;

@Service
public class InstallService {

	public boolean initDatabase(DatabaseConnection databaseConnection) {
		String basePath = Path.getPhysicalPath() + "/WEB-INF/sql/"
				+ databaseConnection.getDbType();
		Properties props = new OrderedProperties();
		try {
			props.load(new FileInputStream(basePath + "/sql.properties"));
			Connection conn = getConnection(databaseConnection);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setAutoCommit(true);

			for (Object key : props.keySet()) {
				File sqlFile = new File(basePath + "/" + String.valueOf(key));
				runner.setSendFullScript(Boolean.parseBoolean(String
						.valueOf(props.get(key))));
				runner.runScript(new InputStreamReader(new FileInputStream(
						sqlFile), "UTF-8"));
			}
			runner.closeConnection();
		} catch (Exception e) {
			throw new BusinessException("执行初始化sql时出错!请清空数据库后再次初始化:"
					+ JsonUtil.clearString(e.getMessage()));
		}
		return true;
	}
	
	public void setConfig(DatabaseConnection databaseConnection){
		File file = new File(Path.getPhysicalPath()+"/WEB-INF/classes/config.properties");
		try {
			
	        PropertiesConfiguration config = new PropertiesConfiguration();
	        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
	        layout.load(new InputStreamReader(new FileInputStream(file)));

	        config.setProperty("install.installed", "true");
			
	        config.setProperty("dataSource.databaseType",databaseConnection.getDbType() );
	        config.setProperty("dataSource.name", databaseConnection.getDataSource());
	        config.setProperty("containerDataSource.jndi", databaseConnection.getJndiName());
	        config.setProperty("innerDataSource.driver", databaseConnection.getDbDriver());
	        config.setProperty("innerDataSource.url", databaseConnection.getDbUrl());
	        config.setProperty("innerDataSource.username", databaseConnection.getDbUser());
	        config.setProperty("innerDataSource.password", databaseConnection.getDbPassword());
	        
	        layout.save(new FileWriter(file));
		} catch (Exception e) {
			throw new BusinessException("设置配置文件时出错!请清空数据库后再次初始化:"
					+ JsonUtil.clearString(e.getMessage()));
		}
	}

	private Connection getConnection(DatabaseConnection databaseConnection) {
		Connection conn = null;

		try {
			Class.forName(databaseConnection.getDbDriver());
			conn = DriverManager.getConnection(databaseConnection.getDbUrl(),
					databaseConnection.getDbUser(),
					databaseConnection.getDbPassword());
		} catch (Exception e) {
			String errorMessage = e.getMessage();
			throw new BusinessException("连接数据库出错："
					+ JsonUtil.clearString(errorMessage));
		}
		return conn;
	}

}
