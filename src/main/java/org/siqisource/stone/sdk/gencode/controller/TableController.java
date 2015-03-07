package org.siqisource.stone.sdk.gencode.controller;

import java.util.List;

import org.siqisource.stone.sdk.gencode.model.Table;
import org.siqisource.stone.sdk.gencode.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TableController {

	@Autowired
	TableService service;

	@RequestMapping("/code/tableList.do")
	public String tableList(Integer projectId, Model model) throws Exception {
		List<Table> tableList = service.list(projectId);
		model.addAttribute("tableList", tableList);
		return "code/TableList";
	}

	@RequestMapping("/code/tableConfig.do")
	public String tableConfig(Integer projectId, String tableName, Model model)
			throws Exception {
		Table table = service.read(projectId, tableName);
		model.addAttribute("table", table);
		return "code/TableConfig";
	}

}
