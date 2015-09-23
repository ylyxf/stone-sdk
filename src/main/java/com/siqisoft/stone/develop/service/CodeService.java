package com.siqisoft.stone.develop.service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.exceptions.ProgramException;
import org.siqisource.stone.utils.ContextTemplate;
import org.siqisource.stone.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.develop.model.CodeFile;
import com.siqisoft.stone.develop.model.GenerateCodeParams;
import com.siqisoft.stone.develop.model.Project;
import com.siqisoft.stone.develop.model.Table;

@Service
public class CodeService {

	@Autowired
	Project project;

	@Autowired
	private Path path;

	@Autowired
	private ContextTemplate template;

	@Autowired
	private DatabaseService databaseService;

	private Map<String, CodeFile> codeFileMap = new HashMap<String, CodeFile>();

	private static final String DEFALUT_CODE_XML = "/WEB-INF/templates/code/code.xml";

	public List<String> readCodeFileNameList() {
		List<String> codeFileNames = new ArrayList<String>();
		try {
			String content = readCodeXmlAsString();
			Document doc = DocumentHelper.parseText(content);
			Element root = doc.getRootElement();
			Iterator<Element> files = root.elementIterator("file");
			while (files.hasNext()) {
				Element file = files.next();
				String name = file.attributeValue("name");
				String src = file.attributeValue("src");
				String dist = file.attributeValue("dist");
				CodeFile codeFile = new CodeFile();
				codeFile.setName(name);
				codeFile.setSrc(src);
				codeFile.setDist(dist);
				if (validCodeFileSrc(codeFile)) {
					codeFileNames.add(name);
					codeFileMap.put(name, codeFile);
				}
			}
		} catch (Exception e) {
			throw new BusinessException("解析Code模板时出错" + e.getLocalizedMessage());
		}
		return codeFileNames;
	}

	private boolean validCodeFileSrc(CodeFile codeFile) {
		try {
			String src = template.parse(codeFile.getSrc());
			URL srcURL = new URL(src);
			InputStream in = srcURL.openStream();
			in.close();
			return true;
		} catch (Exception e) {
			throw new ProgramException("无法定位code模板：" + codeFile.getSrc(), e);
		}
	}

	private String readCodeFileSrc(String srcFileName) {
		try {
			URL srcURL = new URL(srcFileName);
			InputStream in = srcURL.openStream();
			String content = IOUtils.toString(in);
			in.close();
			return content;
		} catch (Exception e) {
			throw new ProgramException("无法定位code模板：" + srcFileName, e);
		}
	}

	private String readCodeXmlAsString() {
		try {
			String configCodeXml = project.getCodeTemplate();
			if (StringUtils.isBlank(configCodeXml)) {
				configCodeXml = "file:///" + path.getPhysicalPath()
						+ DEFALUT_CODE_XML;
			}
			URL codeXmlFile = new URL(configCodeXml);
			String content = IOUtils.toString(codeXmlFile.openStream());
			return content;
		} catch (Exception e) {
			throw new BusinessException("使用freemaker解析codeXmlFile模板时出错", e);
		}
	}

	public void generateCode(GenerateCodeParams generateCodeParams) {
		String basicPackage = this.project.getBasicPackage();
		if (StringUtils.isNotBlank(basicPackage)) {
			String basicPath = basicPackage.replaceAll("[.]", "/");
			this.project.setBasicPath(basicPath);
		}
		
		List<String> tableNameList = generateCodeParams.getTableNameList();
		List<String> codeFileList = generateCodeParams.getCodeFileNameList();
		String modulePackageName = generateCodeParams.getModulePackageName();
		String ignorePrefix = generateCodeParams.getIgnorePrefix();
		for (String tableName : tableNameList) {
			Table table = databaseService.read(tableName, ignorePrefix);
			table.setModulePackageName(modulePackageName);
			table.setModulePath(modulePackageName.replaceAll("[.]", "/"));
			for (String codeFileName : codeFileList) {
				try {
					CodeFile codeFile = this.codeFileMap.get(codeFileName);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("dbtype", table);
					map.put("table", table);
					map.put("project", project);
					// 源文件
					String srcFileName = template.parse(codeFile.getSrc());
					String srcContent = readCodeFileSrc(srcFileName);
					// 目标文件内容
					String distFileContent = template.parse(codeFileName,
							srcContent, map);
					// 目标文件
					String distFileName = template.parse("distFileName",
							codeFile.getDist(), map);
					FileUtils.writeStringToFile(new File(distFileName),
							distFileContent,"utf-8");
				} catch (Exception e) {
					throw new BusinessException("使用freemaker解析codeFile模板时出错", e);
				}
			}
		}
	}
}
