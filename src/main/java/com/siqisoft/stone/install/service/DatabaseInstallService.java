package com.siqisoft.stone.install.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.JsonUtil;
import org.siqisource.stone.utils.OrderedProperties;
import org.siqisource.stone.web.Path;
import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.install.modle.DatabaseConnection;

@Service
@JspService
public class DatabaseInstallService {

	@Autowired
	private Path path;

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

	public boolean runSql(DatabaseConnection databaseConnection) {

		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		String basePath = "/sql-install/" + databaseConnection.getDbType();
		Properties props = new OrderedProperties();
		try {
			props.load(new ClassPathResource(basePath + "/sql.properties")
					.getInputStream());
			for (Object key : props.keySet()) {
				String fileName = basePath + "/" + String.valueOf(key);
				rdp.addScript(new ClassPathResource(fileName));
			}

			Connection conn = getConnection(databaseConnection);
			rdp.populate(conn); // this starts the script execution,
								// in the order as added
		} catch (Exception e) {
			throw new BusinessException("执行初始化sql时出错!"
					+ JsonUtil.clearString(e.getMessage()));
		}
		return true;
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

	public String zipInstallSql(String databaseType) {
		String destPath = path.getPhysicalPath()
				+ "/WEB-INF/download/install-sql" + System.currentTimeMillis()
				+ ".zip";

		String basePath = "/sql-install/" + databaseType;
		Properties props = new OrderedProperties();
		try {
			// 准备好压缩包
			CheckedOutputStream cos = new CheckedOutputStream(
					new FileOutputStream(destPath), new CRC32());
			ZipOutputStream zos = new ZipOutputStream(cos);

			// 加载要执行的sql语句
			props.load(new ClassPathResource(basePath + "/sql.properties")
					.getInputStream());

			// 遍历sql语句文件
			int sortNo = 1;
			for (Object key : props.keySet()) {

				// 定义压缩包中的文件夹名
				String fileName = String.valueOf(key);
				ZipEntry entry = new ZipEntry(sortNo++ + "_" + fileName);
				zos.putNextEntry(entry);

				// 打包文件
				BufferedInputStream bis = new BufferedInputStream(
						new ClassPathResource(basePath + "/" + fileName)
								.getInputStream());
				int count;
				byte data[] = new byte[1024];
				while ((count = bis.read(data, 0, 1024)) != -1) {
					zos.write(data, 0, count);
				}
				bis.close();
				zos.closeEntry();
			}
			zos.close();
		} catch (Exception e) {
			throw new BusinessException("准备sql文件时出错!"
					+ JsonUtil.clearString(e.getMessage()));
		}
		return destPath;
	}
}
