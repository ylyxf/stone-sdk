package com.siqisoft.stone.admin.config.controller;

import java.util.ArrayList;

import org.siqisource.stone.config.model.Config;

public class ConfigQueryForm extends Config {
	
	ArrayList<Config> configList;

	public ArrayList<Config> getConfigList() {
		return configList;
	}

	public void setConfigList(ArrayList<Config> configList) {
		this.configList = configList;
	}
	
}
