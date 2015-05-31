package com.siqisoft.stone.develop.model;

import java.util.HashMap;
import java.util.Map;

public class ArchetypeFile {

	private String src;

	private String dist;

	private boolean asResource = false;

	private boolean asTemplate = false;

	Map<String, String> replace = new HashMap<String, String>();

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public boolean isAsResource() {
		return asResource;
	}

	public void setAsResource(boolean asResource) {
		this.asResource = asResource;
	}

	public boolean isAsTemplate() {
		return asTemplate;
	}

	public void setAsTemplate(boolean asTemplate) {
		this.asTemplate = asTemplate;
	}

	public Map<String, String> getReplace() {
		return replace;
	}

	public void setReplace(Map<String, String> replace) {
		this.replace = replace;
	}

	public void addReplace(String from, String to) {
		this.replace.put(from, to);
	}

}
