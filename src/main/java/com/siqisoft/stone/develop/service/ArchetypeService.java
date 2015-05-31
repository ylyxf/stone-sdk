package com.siqisoft.stone.develop.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.utils.ContextTemplate;
import org.siqisource.stone.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siqisoft.stone.develop.model.ArchetypeFile;
import com.siqisoft.stone.develop.model.Project;

@Service
public class ArchetypeService {

	@Autowired
	private Project project;
	
	@Autowired
	private Path path;
	
	@Autowired
	private ContextTemplate template;

	private List<ArchetypeFile> archetypeFileList = new ArrayList<ArchetypeFile>();

	public void initProject() {
		try {
			String archeTypeXml = readArcheTypeAsString();
			Document doc = DocumentHelper.parseText(archeTypeXml);
			Element root = doc.getRootElement();
			analyseFileElement(root);
			analyseFolderElement(root);
			for (ArchetypeFile archetypeFile : archetypeFileList) {
				processFile(archetypeFile);
			}
		} catch (Exception e) {
			throw new BusinessException("创建archetype时出错", e);
		}
	}

	@SuppressWarnings("unchecked")
	public void analyseFileElement(Element root) {
		Iterator<Element> files = root.elementIterator("file");
		while (files.hasNext()) {
			ArchetypeFile archetypeFile = new ArchetypeFile();
			archetypeFileList.add(archetypeFile);
			Element file = files.next();
			String src = file.attributeValue("src");
			String dist = file.attributeValue("dist");
			String asResource = file.attributeValue("asResource");
			String asTemplate = file.attributeValue("asTemplate");

			archetypeFile.setSrc(src);
			archetypeFile.setDist(dist);
			if ("true".equals(asResource)) {
				archetypeFile.setAsResource(true);
			}
			if ("true".equals(asTemplate)) {
				archetypeFile.setAsTemplate(true);
			}

			Iterator<Element> replaces = file.elementIterator("replace");
			while (replaces.hasNext()) {
				Element replace = replaces.next();
				String from = replace.attributeValue("from");
				String to = replace.attributeValue("to");
				archetypeFile.addReplace(from, to);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void analyseFolderElement(Element root) throws IOException {
		List<ArchetypeFile> tempFileList = new ArrayList<ArchetypeFile>();
		Iterator<Element> folders = root.elementIterator("folder");
		while (folders.hasNext()) {
			Element folder = folders.next();
			String srcDir = folder.attributeValue("src");
			String distDir = folder.attributeValue("dist");

			String asResource = folder.attributeValue("asResource");
			String asTemplate = folder.attributeValue("asTemplate");

			if ("true".equals(asResource)) {
				throw new BusinessException("不支持asResource类型的文件夹");
			} else {
				Collection<File> files = FileUtils.listFiles(new File(srcDir),
						null, true);
				for (File src : files) {
					ArchetypeFile archetypeFile = new ArchetypeFile();
					tempFileList.add(archetypeFile);
					archetypeFile.setSrc(src.getAbsolutePath());
					archetypeFile.setDist(distDir + "/" + src.getName());
					archetypeFile.setAsResource(false);
				}
			}

			for (ArchetypeFile archetypeFile : tempFileList) {

				if ("true".equals(asTemplate)) {
					archetypeFile.setAsTemplate(true);
				}

				Iterator<Element> replaces = folder.elementIterator("replace");
				while (replaces.hasNext()) {
					Element replace = replaces.next();
					String from = replace.attributeValue("from");
					String to = replace.attributeValue("to");
					archetypeFile.addReplace(from, to);
				}
			}

			archetypeFileList.addAll(tempFileList);

		}
	}

	private void processFile(ArchetypeFile file) throws Exception {
		String src = file.getSrc();
		File srcFile = new File(src);
		String dist = file.getDist();
		File distFile = new File(dist);

		if (file.isAsResource()) {
			InputStream srcInput = this.getClass().getClassLoader()
					.getResourceAsStream(src);
			String strString = IOUtils.toString(srcInput);
			FileUtils.writeStringToFile(distFile, strString);
		} else {
			FileUtils.copyFile(srcFile, distFile);
		}

		if (file.isAsTemplate()) {
			String content = FileUtils.readFileToString(distFile);
			FileUtils.writeStringToFile(distFile, template.parse(distFile.getName(),content));
		}

		Map<String, String> replaces = file.getReplace();
		for (String from : replaces.keySet()) {
			String to = replaces.get(from);
			String content = FileUtils.readFileToString(distFile);
			content = content.replaceAll(from, to);
			FileUtils.writeStringToFile(distFile, content);
		}
	}

	private String readArcheTypeAsString() {
		try {
			File archetypeFile = new File(path.getPhysicalPath()
					+ "/WEB-INF/templates/archetype/archetype.xml");
			String content = FileUtils.readFileToString(archetypeFile);
			return template.parse(archetypeFile.getName(),content);
		} catch (Exception e) {
			throw new BusinessException("使用freemaker解析archetype模板时出错", e);
		}
	}

}
