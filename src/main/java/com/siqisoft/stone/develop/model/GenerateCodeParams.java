package com.siqisoft.stone.develop.model;

import java.util.List;

public class GenerateCodeParams {

	private String modulePackageName;

	private String ignorePrefix;

	private List<String> tableNameList;

	private List<String> codeFileNameList;
	
	
	public String getIgnorePrefix() {
		return ignorePrefix;
	}

	public void setIgnorePrefix(String ignorePrefix) {
		this.ignorePrefix = ignorePrefix;
	}

	public String getModulePackageName() {
		return modulePackageName;
	}

	public void setModulePackageName(String modulePackageName) {
		this.modulePackageName = modulePackageName;
	}

	public List<String> getTableNameList() {
		return tableNameList;
	}

	public void setTableNameList(List<String> tableNameList) {
		this.tableNameList = tableNameList;
	}

	public List<String> getCodeFileNameList() {
		return codeFileNameList;
	}

	public void setCodeFileNameList(List<String> codeFileNameList) {
		this.codeFileNameList = codeFileNameList;
	}

}
