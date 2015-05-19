package com.siqisoft.stone.install.service;

import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@JspService
public class InstallCheckService {
	
	@Value("${install.installed}")
	private String installed;

	public String getInstalled() {
		return installed;
	}

	public void setInstalled(String installed) {
		this.installed = installed;
	}
	
}
