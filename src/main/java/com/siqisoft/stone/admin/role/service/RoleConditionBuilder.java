package com.siqisoft.stone.admin.role.service;

import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;

import com.siqisoft.stone.admin.role.controller.RoleQueryForm;

public class RoleConditionBuilder {

	public static Condition listCondition(RoleQueryForm roleQueryForm) {

		SimpleCondition condition = new SimpleCondition();
 
		condition.orderAsc("sortNo");
		return condition;
	}
}

