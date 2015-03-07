package org.siqisource.stone.sdk.gencode.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.siqisource.stone.sdk.gencode.model.Table;
import org.siqisource.stone.sdk.project.model.ProjectView;
import org.siqisource.stone.sdk.project.service.ProjectService;
import org.siqisource.stone.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class CodeService {

	@Autowired
	private ProjectService projectService;

	@Autowired
	TableService tableService;

	@Autowired
	private Path path;

	public void saveCode(Table tableParam) throws IOException {
		Map<String, String> codes = generateCode(tableParam);
		for (String fileName : codes.keySet()) {
			String conetent = codes.get(fileName);
			if (new File(fileName).exists()) {
				fileName += "." + System.currentTimeMillis();
			}
			FileUtils.write(new File(fileName), conetent, "UTF-8");
		}
	}

	public Map<String, String> generateCode(Table tableParam)
			throws IOException {
		Map<String, String> codes = new HashMap<String, String>();

		int projectId = tableParam.getProjectId();
		ProjectView project = this.projectService.readProjectView(projectId);

		String tableName = tableParam.getName();
		Table table = tableService.read(projectId, tableName);
		tableService.mergerTable(table, tableParam);

		File templateDir = new File(path.getPhysicalPath()
				+ "/WEB-INF/templates/code");

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("project", project);
		root.put("table", table);

		Collection<File> files = FileUtils.listFiles(templateDir, null, true);
		for (File template : files) {
			try {
				generateConetent(root, template, codes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return codes;
	}

	private void generateConetent(Map<String, Object> root, File file,
			final Map<String, String> codes) throws Exception {
		List<String> lines = FileUtils.readLines(file);
		if (lines.size() <= 0) {
			return;
		}
		String filePath = getFilePath(root, lines.get(0));

		StringBuffer content = new StringBuffer();
		String lineBreaker = System.getProperty("line.separator");
		for (int i = 1, iSize = lines.size(); i < iSize; i++) {
			// 调过第一行
			content.append(lines.get(i));
			content.append(lineBreaker);
		}
		Template template = new Template(file.getName(), new StringReader(
				content.toString()), new Configuration(
				Configuration.getVersion()));
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		codes.put(filePath, writer.toString());

	}

	private String getFilePath(Map<String, Object> root, String templatePath)
			throws Exception {
		Template template = new Template("fileName", new StringReader(
				templatePath), new Configuration(Configuration.getVersion()));
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		return writer.toString();
	}
}
