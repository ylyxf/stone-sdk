package com.siqisoft.stone.admin.config.controller;

import java.util.ArrayList;

import org.siqisource.stone.config.model.ConfigEntity;

public class ConfigQueryForm extends ConfigEntity {
	
	ArrayList<ConfigEntity> configList;

	public ArrayList<ConfigEntity> getConfigList() {
		return configList;
	}

	public void setConfigList(ArrayList<ConfigEntity> configList) {
		this.configList = configList;
	}
	
}
