package com.siqisoft.stone.develop.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	/**
	 * 数据库表名：st_table_name
	 */
	private String name = "";

	/**
	 * 数据库注释
	 */
	private String comment = "";

	/**
	 * 数据库表名,初始化时与表名相同
	 */
	private String label = "";

	/**
	 * 模块包名
	 */
	private String modulePackageName = "";

	/**
	 * 模块路径
	 */
	private String modulePath = "";

	/**
	 * 自动生成主键
	 * */
	private boolean autoGenerateKey = true;

	/**
	 * 是否生成树代码
	 * */
	private boolean treeCode = false;

	/**
	 * 实体名,驼峰首字母大写：Entity
	 */
	private String entity = "";

	/**
	 * 变量名,驼峰首字母小写：entity
	 */
	private String lowerEntity = "";

	/**
	 * 列
	 */
	private List<Column> columns;

	/**
	 * 主键列
	 */
	private List<Column> primaryColumns = new ArrayList<Column>();

	/**
	 * 非主键列
	 */
	private List<Column> commonColumns = new ArrayList<Column>();

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAutoGenerateKey() {
		return autoGenerateKey;
	}

	public void setAutoGenerateKey(boolean autoGenerateKey) {
		this.autoGenerateKey = autoGenerateKey;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getLowerEntity() {
		return lowerEntity;
	}

	public void setLowerEntity(String lowerEntity) {
		this.lowerEntity = lowerEntity;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Column> getPrimaryColumns() {
		return primaryColumns;
	}

	public void setPrimaryColumns(List<Column> primaryColumns) {
		this.primaryColumns = primaryColumns;
	}

	public List<Column> getCommonColumns() {
		return commonColumns;
	}

	public void setCommonColumns(List<Column> commonColumns) {
		this.commonColumns = commonColumns;
	}

	public void addPrimaryColumn(Column primaryColumn) {
		this.primaryColumns.add(primaryColumn);
	}

	public void addCommonColumn(Column commonColumn) {
		this.commonColumns.add(commonColumn);
	}

	public String getModulePackageName() {
		return modulePackageName;
	}

	public void setModulePackageName(String modulePackageName) {
		this.modulePackageName = modulePackageName;
	}

	public boolean isTreeCode() {
		return treeCode;
	}

	public void setTreeCode(boolean treeCode) {
		this.treeCode = treeCode;
	}

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
	
}
