package com.siqisoft.stone.develop.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.ui.easyui.Paging;
import org.siqisource.stone.utils.NameConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.siqisoft.stone.develop.model.Column;
import com.siqisoft.stone.develop.model.Table;

@Service
public class DatabaseService {

	@Resource(name = "${dataSource.name}")
	DataSource dataSource;
	
	private static Map<Integer, String> SQL_TYPE_TO_JAVA = new HashMap<Integer, String>();

	private static Map<Integer, String> SQL_TYPE_TO_JDBC = new HashMap<Integer, String>();

	static {
		SQL_TYPE_TO_JAVA.put(Types.NUMERIC, Integer.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.DECIMAL, Float.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.INTEGER, Integer.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.SMALLINT, Integer.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.VARCHAR, String.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.BOOLEAN, Boolean.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.BIT, Boolean.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.DATE, Date.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.TIME, Date.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.TIMESTAMP, Date.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.FLOAT, Float.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.DOUBLE, Double.class.getName());
		SQL_TYPE_TO_JAVA.put(Types.CHAR, Boolean.class.getName());

		Field[] fields = Types.class.getFields();
		for (Field field : fields) {
			try {
				SQL_TYPE_TO_JDBC.put(field.getInt(null), field.getName());
			} catch (Exception e) {
			}
		}
	}


	public Map<String, Object> listTable(Paging paging, String name) {
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
				if (StringUtils.isNotBlank(name)) {//根据关键字过滤
					String tableName = table.getName().toLowerCase();
					if (tableName.indexOf(name.toLowerCase()) == -1) {
						continue;
					}

				}
				tableList.add(table);
			}
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", tableList.size());
		result.put("rows", paging(tableList, paging));
		return result;
	}

	public Table read(String tableName,String ignorePrefix) {
		Table table = new Table();
		try {
			Connection conn = dataSource.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			
			String databaseName = metaData.getDatabaseProductName();
			table.setDatabaseName(databaseName.toLowerCase());
			
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
		ignorePrefix = ignorePrefix==null?"":ignorePrefix.toLowerCase();
		String entiryName = tableName.toLowerCase().replaceFirst(ignorePrefix, "");
		entiryName = NameConverter.columnToProperty(entiryName);
		entiryName = NameConverter.firstLetterUpper(entiryName);
		table.setEntity(entiryName);
		table.setLowerEntity(NameConverter.firstLetterLower(entiryName));
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
				String comment = rs.getString("REMARKS");
				comment = comment==null?"":comment;
				column.setComment(comment);
				column.setLabel(comment);
				column.setName(rs.getString("COLUMN_NAME"));
				column.setSize(rs.getInt("COLUMN_SIZE"));
				column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
				
				String typeName = rs.getString("TYPE_NAME");
				column.setTypeName(typeName);
				
				Integer sqlType = rs.getInt("DATA_TYPE");
				column.setDataType(sqlType);
				
				column.setProperty(NameConverter.columnToProperty(column
						.getName()));
				column.setUpperProperty(NameConverter.firstLetterUpper(column
						.getProperty()));
				
				try{
					String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
					if ("YES".equals(isAutoIncrement)) {
						column.setAutoIncrement(true);
					} else {
						column.setAutoIncrement(false);
					}
				}catch(Exception ex){
					
				}
				
				if ("NUMBER".equals(typeName) && column.getDecimalDigits() == 0) {
					sqlType = Types.NUMERIC;
				} else if ("NUMBER".equals(typeName) && column.getDecimalDigits() > 0) {
					sqlType = Types.FLOAT;
				}
				String javaType = SQL_TYPE_TO_JAVA.get(sqlType);
				if (StringUtils.isNotBlank(javaType)) {
					javaType = javaType.replace("java.lang.", "");
				} else {
					javaType = "未识别的java类型";
				}
				column.setJavaType(javaType);
			 
				column.setJdbcType(SQL_TYPE_TO_JDBC.get(column.getDataType()));
				
				
				columnList.add(column);
				System.out.println(column.toString());
			}
			table.setColumns(columnList);

			// 设置主键列
			this.setPrimaryColums(table);
		} catch (SQLException e) {
			throw new BusinessException("读取表清单时出错：" + e.getMessage());
		}
	}


	private List<Table> paging(List<Table> tableList, final Paging paging) {
		Collections.sort(tableList, new Comparator<Table>() {

			@Override
			public int compare(Table o1, Table o2) {
				String sort = paging.getSort();
				if (StringUtils.isBlank(sort)) {
					return 0;
				}
				String order = paging.getOrder();
				Field field = ReflectionUtils.findField(o1.getClass(), sort);
				field.setAccessible(true);
				String value1 = String.valueOf(ReflectionUtils.getField(field,
						o1));
				String value2 = String.valueOf(ReflectionUtils.getField(field,
						o2));
				String[] values = { value1, value2 };
				Arrays.sort(values);
				if ("desc".equals(order)) {
					return value1.equals(values[0]) ? 1 : -1;
				} else {
					return value1.equals(values[0]) ? -1 : 1;
				}
			}

		});
		int offset = paging.getRowBounds().getOffset();
		int limit = paging.getRowBounds().getLimit();
		if (tableList.size() - offset < limit) {
			limit = tableList.size() - offset;
		}

		List<Table> result = new ArrayList<Table>();
		for (int i = 0; i < limit; i++) {
			result.add(tableList.get(offset + i));
		}
		return result;
	}
}
