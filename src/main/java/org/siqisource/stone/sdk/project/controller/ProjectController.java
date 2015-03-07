package org.siqisource.stone.sdk.project.controller;

import java.util.List;

import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.service.ProjectDatabaseService;
import org.siqisource.stone.sdk.project.service.ProjectFileService;
import org.siqisource.stone.sdk.project.service.ProjectService;
import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.ui.Notify;
import org.siqisource.stone.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectController {

	@Autowired
	ProjectService service;

	@Autowired
	ProjectFileService projectFileService;

	@Autowired
	ProjectDatabaseService projectDatabaseService;

	@RequestMapping("/project/prepareDatabase.do")
	@ResponseBody
	public AjaxResponse prepareDatabase(Integer id) throws Exception {
		projectDatabaseService.prepareDatabase(id);
		return new AjaxResponse("数据库准备成功。");
	}

	@RequestMapping("/project/projectInit.do")
	@ResponseBody
	public AjaxResponse projectInit(Integer id) throws Exception {
		Project project = projectFileService.initProject(id);
		return new AjaxResponse("成功生成项目:" + project.getProjectPath()
				+ "。可直接在Eclipse中导入");
	}

	@RequestMapping("/project/ProjectList.do")
	public String list(Model model) {
		List<Project> projectList = this.service.list(new SimpleCondition());
		model.addAttribute("projectList", projectList);
		return "project/ProjectList";
	}

	@RequestMapping("/project/ProjectRead.do")
	public String read(Integer id, Model model) {
		Project project = service.read(id);
		model.addAttribute("project", project);
		return "project/ProjectRead";
	}

	@RequestMapping("/project/ProjectAddInit.do")
	public String addInit(Project project, Model model) {
		return "project/ProjectAdd";
	}

	@RequestMapping("/project/projectAdd.do")
	public String add(Project project, Model model) {
		service.insert(project);
		model.addAttribute("notify", JsonUtil.toJson(new Notify("新增成功")));
		return this.read(project.getId(), model);
	}

	@RequestMapping("/project/projectDelete.do")
	public String delete(Integer id, Model model) {
		service.delete(id);
		model.addAttribute("notify", JsonUtil.toJson(new Notify("删除成功")));
		return this.list(model);
	}

	@RequestMapping("/project/ProjectEditInit.do")
	public String editInit(Integer id, Model model) {
		Project project = service.read(id);
		model.addAttribute("project", project);
		return "project/ProjectEdit";
	}

	@RequestMapping("/project/projectEdit.do")
	public String edit(Project project, Model model) {
		service.update(project);
		model.addAttribute("notify", JsonUtil.toJson(new Notify("更新成功")));
		return this.read(project.getId(), model);
	}

}
