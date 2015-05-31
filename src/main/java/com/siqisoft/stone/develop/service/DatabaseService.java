package com.siqisoft.stone.develop.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.NameConverter;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.develop.model.Column;
import com.siqisoft.stone.develop.model.Table;
import com.siqisoft.stone.develop.utils.JdbcUtils;

@Service
public class DatabaseService {
	@Resource(name = "${dataSource.name}")
	DataSource dataSource;

	public List<Table> listTable() {
		List<Table> tableList = new ArrayList<Table>();
		try {
			Connection conn = dataSource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables(null, null, null,
					new String[] { "TABLE" });
			while (rs.next()) {
				Table table = new Table();
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

	public Table read(String tableName) {
		Table table = new Table();
		try {
			Connection conn = dataSource.getConnection();
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
		if (indexPos <= 3) {
			entiryName = entiryName.substring(indexPos);
		}
		entiryName = NameConverter.columnToProperty(entiryName);
		entiryName = NameConverter.firstLetterUpper(entiryName);

		table.setEntity(entiryName);
		this.setColums(table);
		return table;
	}

	public void setPrimaryColums(Table table) {
		List<String> primaryColumnList = new ArrayList<String>();

		try {
			Connection conn = dataSource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getPrimaryKeys(null, null, table.getName());
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");
				primaryColumnList.add(columnName);
			}
			List<Column> columnList = table.getColumns();

			for (Column column : columnList) {
				if (primaryColumnList.contains(column.getName())) {
					column.setPrimaryKey(true);
					table.addPrimaryColumn(column);

					// 如果主键是自增的，那么表的主键是自增的
					if (column.getAutoIncrement() != null
							&& column.getAutoIncrement()) {
						table.setAutoGenerateKey(true);
					}

				} else {
					column.setPrimaryKey(false);
					table.addCommonColumn(column);
				}
			}
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
	}

	public void setColums(Table table) {
		List<Column> columnList = new ArrayList<Column>();
		try {
			Connection conn = dataSource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getColumns(null, null, table.getName(),
					null);
			while (rs.next()) {
				Column column = new Column();
				column.setComment(rs.getString("REMARKS"));
				column.setLabel(column.getComment());
				column.setName(rs.getString("COLUMN_NAME"));
				column.setDataType(rs.getString("TYPE_NAME"));
				column.setSize(rs.getInt("COLUMN_SIZE"));
				column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
				column.setJdbcType(JdbcUtils.getJdbcType(rs.getInt("DATA_TYPE")));
				String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
				if ("YES".equals(isAutoIncrement)) {
					column.setAutoIncrement(true);
				} else {
					column.setAutoIncrement(false);
				}

				column.setJavaType(JdbcUtils.getJavaType(rs.getInt("DATA_TYPE")));

				column.setProperty(NameConverter.columnToProperty(column
						.getName()));
				column.setUpperProperty(NameConverter.firstLetterUpper(column
						.getProperty()));
				columnList.add(column);
			}
			table.setColumns(columnList);

			// 设置主键列
			this.setPrimaryColums(table);
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
	}

	public void mergerTable(Table table, Table tableParam) {
		table.setLabel(tableParam.getLabel());
		table.setModulePackageName(tableParam.getModulePackageName());
		table.setModulePath(tableParam.getModulePackageName().replaceAll("[.]",
				"/"));
		table.setEntity(tableParam.getEntity());
		table.setLowerEntity(NameConverter.firstLetterLower(tableParam
				.getEntity()));
		table.setAutoGenerateKey(tableParam.isAutoGenerateKey());
		table.setTreeCode(tableParam.isTreeCode());
	}

}
