package com.siqisoft.stone.develop.model;

import org.siqisource.stone.config.annotation.Config;
import org.siqisource.stone.config.annotation.ConfigClass;
import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.stereotype.Component;

@Component
@JspService
@ConfigClass(label="项目信息")
public class Project { 

	@Config(label="项目名称")
	private String name;

	@Config(label="项目路径")
	private String path;

	@Config(label="基本包名")
	private String basicPackage;
	
	@Config(label="代码模板")
	private String codeTemplate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBasicPackage() {
		return basicPackage;
	}

	public void setBasicPackage(String basicPackage) {
		this.basicPackage = basicPackage;
	}

	public String getCodeTemplate() {
		return codeTemplate;
	}

	public void setCodeTemplate(String codeTemplate) {
		this.codeTemplate = codeTemplate;
	}

	public String getBasicPackagePath() {
		return basicPackage.replaceAll("[.]", "/");
	}

}
