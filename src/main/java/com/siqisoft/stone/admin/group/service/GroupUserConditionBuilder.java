package com.siqisoft.stone.admin.group.service;

import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;

import com.siqisoft.stone.admin.user.controller.GroupUserQueryForm;

public class GroupUserConditionBuilder {

	public static Condition listCondition(GroupUserQueryForm groupUserQueryForm) {

		SimpleCondition condition = new SimpleCondition();
		
		condition.orderDesc("id");
		
		return condition;
	}
}
