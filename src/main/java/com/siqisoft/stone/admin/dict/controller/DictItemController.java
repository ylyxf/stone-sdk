package com.siqisoft.stone.admin.dict.controller;

import java.util.List;

import org.siqisource.stone.dict.model.Dict;
import org.siqisource.stone.dict.model.DictItem;
import org.siqisource.stone.dict.service.DictItemService;
import org.siqisource.stone.dict.service.DictService;
import org.siqisource.stone.orm.condition.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqisoft.stone.admin.dict.service.DictItemConditionBuilder;

@Controller
public class DictItemController {

	@Autowired
	DictItemService service;

	@Autowired
	DictService dictService;

	@RequestMapping("/dict/DictItemList.do")
	public String list(String dictCode, Model model) {
		
		Dict dict = dictService.read(dictCode);
		model.addAttribute("dict", dict);

		return "dict/DictItemList";
	}

	@RequestMapping("/dict/dictItemListData.do")
	@ResponseBody
	public List<DictItem> listData(DictItemQueryForm dictItemQueryForm) {
		Condition condition = DictItemConditionBuilder
				.listCondition(dictItemQueryForm);
		List<DictItem> dictItemList = service.list(condition);
		return dictItemList;
	}

	@RequestMapping("/dict/DictItemRead.do")
	public String read(Integer id, Model model) {
		DictItem dictItem = this.service.read(id);
		model.addAttribute("dictItem", dictItem);
		return "dict/DictItemRead";
	}

	@RequestMapping("/dict/DictItemAddInit.do")
	public String addInit(DictItem dictItem, Model model) {
		return "dict/DictItemAdd";
	}

	@RequestMapping("/dict/DictItemAdd.do")
	public String add(DictItem dictItem, Model model) {
		service.insert(dictItem);
		return this.read(dictItem.getId(), model);
	}

	@RequestMapping("/dict/dictItemDelete.do")
	@ResponseBody
	public Integer delete(Integer[] id, Model model) {
		// 判断是否被关联
		if (id != null && id.length > 0) {
			service.deleteBatch(id);
			return id.length;
		}
		return 0;
	}

	@RequestMapping("/dict/DictItemEditInit.do")
	public String editInit(Integer id, Model model) {
		DictItem dictItem = service.read(id);
		model.addAttribute("dictItem", dictItem);
		return "dict/DictItemEdit";
	}

	@RequestMapping("/dict/DictItemEdit.do")
	public String edit(DictItem dictItem, Model model) {
		service.update(dictItem);
		return this.read(dictItem.getId(), model);
	}

}
