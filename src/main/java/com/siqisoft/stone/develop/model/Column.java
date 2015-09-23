package com.siqisoft.stone.develop.model;

public class Column {

	/**
	 * 列标题
	 */
	private String label = "";

	/**
	 * 注释
	 */
	private String comment = "";

	/**
	 * 列名
	 */
	private String name;

	/**
	 * 属性名
	 */
	private String property;

	/**
	 * 大写属性名
	 */
	private String upperProperty;

	/**
	 * 是否主键
	 */
	private Boolean primaryKey;

	/**
	 * 列类型
	 */
	private Integer dataType;
	
	/**
	 * 列类型
	 */
	private String typeName;

	/**
	 * java类型
	 */
	private String javaType;

	/**
	 * jdbc类型
	 */
	private String jdbcType;

	/**
	 * 长度
	 */
	private int size;

	/**
	 * 小数部分的位数
	 */
	private int decimalDigits;

	/**
	 * 是否自增长
	 */
	private Boolean autoIncrement = false;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

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

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getUpperProperty() {
		return upperProperty;
	}

	public void setUpperProperty(String upperProperty) {
		this.upperProperty = upperProperty;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public Boolean getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(Boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getDataType() {
		return dataType;
	}

	@Override
	public String toString() {
		return "Column [label=" + label + ", comment=" + comment + ", name="
				+ name + ", property=" + property + ", upperProperty="
				+ upperProperty + ", primaryKey=" + primaryKey + ", dataType="
				+ dataType + ", typeName=" + typeName + ", javaType="
				+ javaType + ", jdbcType=" + jdbcType + ", size=" + size
				+ ", decimalDigits=" + decimalDigits + ", autoIncrement="
				+ autoIncrement + "]";
	}
	
}
