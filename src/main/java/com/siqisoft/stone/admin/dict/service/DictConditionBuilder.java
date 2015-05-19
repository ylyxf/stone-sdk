package com.siqisoft.stone.admin.dict.service;

import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;

import com.siqisoft.stone.admin.dict.controller.DictQueryForm;

public class DictConditionBuilder {

	public static Condition listCondition(DictQueryForm dictQueryForm) {

		SimpleCondition condition = new SimpleCondition();
 
		condition.orderAsc("sortNo");
		return condition;
	}
}

