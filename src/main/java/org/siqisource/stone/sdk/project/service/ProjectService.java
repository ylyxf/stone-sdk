package org.siqisource.stone.sdk.project.service;

import org.siqisource.stone.sdk.StoneVersion;
import org.siqisource.stone.sdk.project.mapper.ProjectMapper;
import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.model.ProjectView;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractService<Project> {

	@Autowired
	ProjectMapper mapper;

	@Override
	protected MybatisMapper<Project> getMapper() {
		return this.mapper;
	}

	public ProjectView readProjectView(int id) {
		Project project = this.read(id);
		ProjectView projectView = new ProjectView();
		projectView.setDbDriver(project.getDbDriver());
		projectView.setDbType(project.getDbType());
		projectView.setDbPassword(project.getDbPassword());
		projectView.setDbUrl(project.getDbUrl());
		projectView.setDbUser(project.getDbUser());
		projectView.setId(id);
		projectView.setName(project.getName());
		projectView.setPackageName(project.getPackageName());
		projectView.setProjectPath(project.getProjectPath());
		projectView.setRemark(project.getRemark());
		String packagePath = projectView.getPackageName().replace('.', '/');
		projectView.setPackagePath(packagePath);
		projectView.setStoneVersion(StoneVersion.CURRENT_SUPPORT);
		return projectView;
	}
}
