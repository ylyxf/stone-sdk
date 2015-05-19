package com.siqisoft.stone.admin.dict.service;

import org.apache.commons.lang.StringUtils;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;

import com.siqisoft.stone.admin.dict.controller.DictItemQueryForm;

public class DictItemConditionBuilder {

	public static Condition listCondition(DictItemQueryForm dictItemQueryForm) {

		SimpleCondition condition = new SimpleCondition();
		
		String dictCode = dictItemQueryForm.getDictCode();
		if(StringUtils.isNotBlank(dictCode)){
			condition.andEqual("dictCode", StringUtils.trim(dictCode));
		}
		 
		condition.orderAsc("sortNo");
		return condition;
	}
}

