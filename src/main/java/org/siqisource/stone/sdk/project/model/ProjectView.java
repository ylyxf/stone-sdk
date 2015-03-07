package org.siqisource.stone.sdk.project.model;

public class ProjectView extends Project {
	
	private String stoneVersion;

	private String packagePath;
	
	public String getStoneVersion() {
		return stoneVersion;
	}

	public void setStoneVersion(String stoneVersion) {
		this.stoneVersion = stoneVersion;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

}
