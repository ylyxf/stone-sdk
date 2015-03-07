package org.siqisource.stone.sdk.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.model.ProjectView;
import org.siqisource.stone.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectDatabaseService {

	@Autowired
	private Path path;

	@Autowired
	private ProjectService projectService;

	public Connection getConnection(Project project) {
		Connection conn = null;

		try {
			Class.forName(project.getDbDriver());
			conn = DriverManager.getConnection(project.getDbUrl(),
					project.getDbUser(), project.getDbPassword());
		} catch (Exception e) {
			throw new BusinessException("连接数据库出错：" + e.getMessage());
		}
		return conn;
	}

	public void prepareDatabase(Integer id) {
		ProjectView projectView = projectService.readProjectView(id);
		File sqlDir = new File(path.getPhysicalPath()
				+ "/WEB-INF/templates/project/sql/" + projectView.getDbType()
				+ "/" + projectView.getStoneVersion());
		Collection<File> files = FileUtils.listFiles(sqlDir, null, true);
		try {
			Connection conn = getConnection(projectView);
			ScriptRunner runner = new ScriptRunner(conn);
			for (File sql : files) {
				Reader reader = new FileReader(sql);
				runner.runScript(new InputStreamReader(
						new FileInputStream(sql), "UTF-8"));
				conn.commit();
				reader.close();
			}
			conn.close();
		} catch (Exception e) {
			throw new BusinessException("执行初始化sql时出错!请清空数据库后再次初始化:"
					+ e.getMessage());
		}

	}

}
