package org.siqisource.stone.sdk.gencode.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.exceptions.ProgramException;
import org.siqisource.stone.sdk.gencode.model.Column;
import org.siqisource.stone.sdk.gencode.model.Table;
import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.service.ProjectDatabaseService;
import org.siqisource.stone.utils.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {

	@Autowired
	private ProjectDatabaseService projectDatabaseService;

	private static Map<Integer, String> TYPE_MAP = new HashMap<Integer, String>();

	static {
		TYPE_MAP.put(Types.NUMERIC, Integer.class.getName());
		TYPE_MAP.put(Types.DECIMAL, Float.class.getName());
		TYPE_MAP.put(Types.INTEGER, Integer.class.getName());
		TYPE_MAP.put(Types.SMALLINT, Integer.class.getName());
		TYPE_MAP.put(Types.VARCHAR, String.class.getName());
		TYPE_MAP.put(Types.BOOLEAN, Boolean.class.getName());
		TYPE_MAP.put(Types.DATE, Date.class.getName());
		TYPE_MAP.put(Types.TIME, Date.class.getName());
		TYPE_MAP.put(Types.TIMESTAMP, Date.class.getName());
		TYPE_MAP.put(Types.FLOAT, Float.class.getName());
		TYPE_MAP.put(Types.DOUBLE, Double.class.getName());
	}

	public void setPrimaryColums(Project project, Table table) {
		List<String> primaryColumnList = new ArrayList<String>();
		Connection conn = projectDatabaseService.getConnection(project);
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getPrimaryKeys(null, null, table.getName());
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");
				primaryColumnList.add(columnName);
			}
			List<Column> columnList = table.getColumns();
			for (String primaryKey : primaryColumnList) {
				for (Column column : columnList) {
					if (primaryKey.equals(column.getName())) {
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
			}
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
	}

	public void setColums(Project project, Table table) {
		List<Column> columnList = new ArrayList<Column>();
		Connection conn = projectDatabaseService.getConnection(project);
		try {
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
				column.setJdbcType(getJdbcType(rs.getInt("DATA_TYPE")));
				String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
				if ("YES".equals(isAutoIncrement)) {
					column.setAutoIncrement(true);
				} else {
					column.setAutoIncrement(false);
				}

				column.setJavaType(TYPE_MAP.get(rs.getInt("DATA_TYPE")));

				column.setProperty(NameConverter.columnToProperty(column
						.getName()));
				column.setUpperProperty(NameConverter.firstLetterUpper(column
						.getProperty()));
				columnList.add(column);
			}
			table.setColumns(columnList);

			// 设置主键列
			this.setPrimaryColums(project, table);
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
	}

	private String getJdbcType(Integer jdbcType) {
		Field[] fields = Types.class.getFields();
		for (Field field : fields) {
			try {
				if (jdbcType == field.getInt(null)) {
					return field.getName();
				}
			} catch (Exception e) {
				throw new ProgramException("根据jdbcType找到对应的字符时出错了");
			}
		}
		return "";
	}
}
