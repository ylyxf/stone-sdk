package org.siqisource.stone.sdk.gencode.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.sdk.gencode.model.Table;
import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.service.ProjectDatabaseService;
import org.siqisource.stone.sdk.project.service.ProjectService;
import org.siqisource.stone.utils.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ColumnService columnService;

	@Autowired
	ProjectDatabaseService projectDatabaseService;

	public List<Table> list(Integer projectId) {
		Project project = projectService.read(projectId);
		List<Table> tableList = new ArrayList<Table>();
		Connection conn = projectDatabaseService.getConnection(project);
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables(null, null, null,
					new String[] { "TABLE" });
			while (rs.next()) {
				Table table = new Table();
				table.setProjectId(projectId);
				table.setLabel(rs.getString("REMARKS"));
				table.setComment(rs.getString("REMARKS"));
				table.setName(rs.getString("TABLE_NAME"));
				tableList.add(table);
			}
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
		return tableList;
	}

	public Table read(Integer projectId, String tableName) {
		Project project = projectService.read(projectId);
		Table table = new Table();
		table.setProjectId(projectId);
		Connection conn = projectDatabaseService.getConnection(project);
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables(null, null, tableName,
					new String[] { "TABLE" });
			if (rs.next()) {
				table.setLabel(rs.getString("REMARKS"));
				table.setComment(rs.getString("REMARKS"));
				table.setName(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			throw new BusinessException("读取表内容时出错：" + e.getMessage());
		}
		
		String entiryName = tableName;
		int indexPos = tableName.indexOf('_');
		if(indexPos<=3){
			entiryName = entiryName.substring(indexPos);
		}
		entiryName = NameConverter.columnToProperty(entiryName);
		entiryName = NameConverter.firstLetterUpper(entiryName);
		
		table.setEntity(entiryName);
		columnService.setColums(project, table);
		return table;
	}

	public void mergerTable(Table table, Table tableParam) {
		table.setLabel(tableParam.getLabel());
		table.setModulePackageName(tableParam.getModulePackageName());
		table.setModulePath(tableParam.getModulePackageName().replaceAll("[.]", "/"));
		table.setEntity(tableParam.getEntity());
		table.setLowerEntity(NameConverter.firstLetterLower(tableParam
				.getEntity()));
		table.setAutoGenerateKey(tableParam.isAutoGenerateKey());
		table.setTreeCode(tableParam.isTreeCode());
	}
}
