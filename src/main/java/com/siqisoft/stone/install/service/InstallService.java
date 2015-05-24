package com.siqisoft.stone.install.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.JsonUtil;
import org.siqisource.stone.utils.OrderedProperties;
import org.siqisource.stone.web.Path;
import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.install.modle.DatabaseConnection;

@Service
@JspService
public class InstallService {

	@Value("${install.installed}")
	private String installed;

	public String getInstalled() {
		return installed;
	}

	public void setInstalled(String installed) {
		this.installed = installed;
	}

	public void checkConnection(DatabaseConnection databaseConnection) {
		this.getConnection(databaseConnection);
	}

	public boolean initDatabase(DatabaseConnection databaseConnection) {
		String basePath = Path.getPhysicalPath() + "/WEB-INF/sql-install/"
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

			File outputFile = new File(configFileURL.toURI());
			layout.save(new FileWriter(outputFile));
			System.out.println(outputFile.getAbsolutePath());
		} catch (Exception e) {
			throw new BusinessException("设置配置文件时出错!请清空数据库后再次初始化:"
					+ JsonUtil.clearString(e.getMessage()));
		}
	}

	public void setAdminPassword(DatabaseConnection databaseConnection,
			String password) {
		try {
			Connection connection = this.getConnection(databaseConnection);
			PreparedStatement pstm = connection
					.prepareStatement("update st_user set password = ? , enabled = ?  where account = ? ");
			pstm.setString(1, password);
			pstm.setBoolean(2, true);
			pstm.setString(3, "admin");
			pstm.executeUpdate();
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			throw new BusinessException("连接数据库出错："
					+ JsonUtil.clearString(errorMessage));
		}
	}

	private Connection getConnection(DatabaseConnection databaseConnection) {
		Connection conn = null;

		String dataSource = databaseConnection.getDataSource();
		if ("containerDataSource".equals(dataSource)) {
			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup(databaseConnection
						.getJndiName());
				conn = ds.getConnection();
			} catch (Exception e) {
				String errorMessage = e.getMessage();
				throw new BusinessException("连接数据库出错："
						+ JsonUtil.clearString(errorMessage));
			}
		}
		if ("innerDataSource".equals(dataSource)) {
			try {
				Class.forName(databaseConnection.getDbDriver());
				conn = DriverManager.getConnection(
						databaseConnection.getDbUrl(),
						databaseConnection.getDbUser(),
						databaseConnection.getDbPassword());
			} catch (Exception e) {
				String errorMessage = e.getMessage();
				throw new BusinessException("连接数据库出错："
						+ JsonUtil.clearString(errorMessage));
			}
		}

		return conn;
	}

}
