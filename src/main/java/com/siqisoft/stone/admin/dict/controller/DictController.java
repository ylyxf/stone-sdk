package com.siqisoft.stone.admin.dict.controller;

import java.util.List;

import org.siqisource.stone.dict.model.Dict;
import org.siqisource.stone.dict.service.DictService;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.ui.AjaxResponse;
import org.siqisource.stone.ui.Notify;
import org.siqisource.stone.ui.easyui.PagedRows;
import org.siqisource.stone.ui.easyui.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.admin.dict.service.DictConditionBuilder;

@Controller
public class DictController {

	@Autowired
	DictService service;

	@RequestMapping("/dict/DictList.do")
	public String list(Model model) {
		return "dict/DictList";
	}

	@RequestMapping("/dict/dictListData.do")
	@ResponseBody
	public PagedRows<Dict> listData(DictQueryForm dictQueryForm, Paging paging) {
		Condition condition = DictConditionBuilder.listCondition(dictQueryForm);
		int count = service.count(condition);
		List<Dict> dictList = service.list(condition, paging.getRowBounds());
		return new PagedRows<Dict>(count, dictList);
	}

	@RequestMapping("/dict/DictRead.do")
	public String read(String code, Model model) {
		Dict dict = service.read(code);
		model.addAttribute("dict", dict);
		return "dict/DictRead";
	}

	@RequestMapping("/dict/DictAddInit.do")
	public String addInit(Dict dict, Model model) {
		return "dict/DictAdd";
	}

	@RequestMapping("/dict/DictAdd.do")
	public String add(Dict dict, Model model) {
		service.insert(dict);
		return this.read(dict.getCode(), model);
	}

	@RequestMapping("/dict/dictDelete.do")
	@ResponseBody
	public AjaxResponse delete(String[] codeList, Model model) {
		// 判断是否被关联
		if (codeList != null) {
			service.deleteBatch(codeList);
		}
		return new Notify("成功删除"+codeList.length+"条记录");
	}

	@RequestMapping("/dict/DictEditInit.do")
	public String editInit(String code, Model model) {
		Dict dict = service.read(code);
		model.addAttribute("dict", dict);
		return "dict/DictEdit";
	}

	@RequestMapping("/dict/DictEdit.do")
	public String edit(Dict dict, Model model) {
		service.update(dict);
		return this.read(dict.getCode(), model);
	}

}
