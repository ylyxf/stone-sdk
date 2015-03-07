package org.siqisource.stone.sdk.gencode.controller;

import java.util.Map;

import org.siqisource.stone.sdk.gencode.model.Table;
import org.siqisource.stone.sdk.gencode.service.CodeService;
import org.siqisource.stone.ui.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class CodeController {

	@Autowired
	CodeService service;

	@RequestMapping("/code/ViewCode.do")
	public String viewCode(Table tableParam, Model model) throws Exception {
		Map<String, String> codes = service.generateCode(tableParam);
		for (String key : codes.keySet()) {
			codes.put(key, HtmlUtils.htmlEscape(codes.get(key)));
		}
		model.addAttribute("codes", codes);

		return "code/ViewCode";
	}

	@RequestMapping("/code/saveCode.do")
	@ResponseBody
	public AjaxResponse saveCode(Table tableParam, Model model)
			throws Exception {
		service.saveCode(tableParam);
		AjaxResponse ajaxResponse = new AjaxResponse("操作成功");
		return ajaxResponse;
	}


}
