package com.siqisoft.stone.admin.dict.controller;

import java.util.List;

import org.siqisource.stone.dict.model.Dict;
import org.siqisource.stone.dict.service.DictService;
import org.siqisource.stone.orm.condition.Condition;
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
	public List<Dict> listData(DictQueryForm dictQueryForm) {
		Condition condition = DictConditionBuilder.listCondition(dictQueryForm);
		List<Dict> dictList = service.list(condition);
		return dictList;
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
	public int delete(String[] code, Model model) {
		// 判断是否被关联
		if(code!=null){
			service.deleteBatch(code);
			return code.length;
		}
		return 0;
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
