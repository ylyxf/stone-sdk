package ${project.basicPackage}.${table.modulePackageName}.controller;

import java.util.List;

import org.siqisource.stone.orm.condition.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${project.basicPackage}.${table.modulePackageName}.model.${table.entity};
import ${project.basicPackage}.${table.modulePackageName}.service.${table.entity}Service;
import ${project.basicPackage}.${table.modulePackageName}.controller.${table.entity}ConditionBuilder;
import ${project.basicPackage}.${table.modulePackageName}.controller.${table.entity}QueryForm;

@Controller
public class ${table.entity}Controller {

	@Autowired
	${table.entity}Service service;

	@RequestMapping("/${table.modulePath}/${table.entity}List.do")
	public String list() {
		return "${table.modulePath}/${table.entity}List";
	}

	@RequestMapping("/${table.modulePath}/${table.entity}ListData.do")
	@ResponseBody
	public  List<${table.entity}> listData(${table.entity}QueryForm ${table.lowerEntity}QueryForm) {
		Condition condition = ${table.entity}ConditionBuilder.listCondition(${table.lowerEntity}QueryForm);
		List<${table.entity}> ${table.lowerEntity}List = service.list(condition);
		return ${table.lowerEntity}List;
	}

	@RequestMapping("/${table.modulePath}/${table.entity}AddInit.do")
	public String addInit(${table.entity} ${table.lowerEntity}, Model model) {
		return "${table.modulePath}/${table.entity}Add";
	}

	@RequestMapping("/${table.modulePath}/${table.lowerEntity}Add.do")
	@ResponseBody
	public boolean add(${table.entity} ${table.lowerEntity}, Model model) {
		service.insert(${table.lowerEntity});
		return true;
	}

	@RequestMapping("/${table.modulePath}/${table.lowerEntity}Delete.do")
	@ResponseBody
	public boolean delete(Integer[] idList, Model model) {
		// 判断是否被关联
		service.logicDeleteBatch(idList);
		return true;
	}

	@RequestMapping("/${table.modulePath}/${table.entity}EditInit.do")
	public String editInit(Integer id, Model model) {
		${table.entity} ${table.lowerEntity} = service.read(id);
		model.addAttribute("${table.lowerEntity}", ${table.lowerEntity});
		return "${table.modulePath}/${table.entity}Edit";
	}

	@RequestMapping("/${table.modulePath}/${table.lowerEntity}Edit.do")
	@ResponseBody
	public boolean edit(${table.entity} ${table.lowerEntity}) {
		service.update(${table.lowerEntity});
		return  true;
	}

}