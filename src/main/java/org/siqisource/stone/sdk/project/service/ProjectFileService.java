package org.siqisource.stone.sdk.project.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.sdk.project.model.Project;
import org.siqisource.stone.sdk.project.model.ProjectView;
import org.siqisource.stone.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class ProjectFileService {

	@Autowired
	private ProjectService service;

	@Autowired
	private Path path;

	public Project initProject(Integer id) throws IOException {

		ProjectView project = this.service.readProjectView(id);
		String destPath = project.getProjectPath();
		File distDir = new File(destPath);
		checkDir(distDir);

		File srcDir = new File(path.getPhysicalPath()
				+ "/WEB-INF/templates/project/folder");

		File templateDir = new File(path.getPhysicalPath()
				+ "/WEB-INF/templates/project/file");

		FileUtils.copyDirectory(srcDir, distDir);

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("project", project);
		Collection<File> files = FileUtils.listFiles(templateDir, null, true);
		for (File template : files) {
			try {
				generateFile(root, template);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return project;
	}

	private void checkDir(File distDir) {
		if (distDir.exists() && distDir.listFiles().length > 0) {
			throw new BusinessException("项目文件夹已存在 ,不能重新初始化。您可以删除项目文件夹后再次生成");
		}
		if (!distDir.exists()) {
			distDir.mkdirs();
		}
	}

	private void generateFile(Map<String, Object> root, File file)
			throws Exception {
		List<String> lines = FileUtils.readLines(file);
		if (lines.size() <= 0) {
			return;
		}
		String filePath = gerFilePath(root, lines.get(0));
		if(new File(filePath).exists()){
			filePath += System.currentTimeMillis();
		}

		StringBuffer content = new StringBuffer();
		String lineBreaker = System.getProperty("line.separator");
		for (int i = 1, iSize = lines.size(); i < iSize; i++) {
			// 调过第一行
			content.append(lines.get(i));
			content.append(lineBreaker);
		}
		Template template = new Template(file.getName(),
				new StringReader(
				content.toString()), new Configuration(
				Configuration.getVersion()));
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		FileUtils.write(new File(filePath), writer.toString());
	}

	private String gerFilePath(Map<String, Object> root, String templatePath)
			throws Exception {
		Template template = new Template("fileName", new StringReader(
				templatePath), new Configuration(Configuration.getVersion()));
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		return writer.toString();
	}
}
