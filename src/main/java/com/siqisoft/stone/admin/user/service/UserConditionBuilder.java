package com.siqisoft.stone.admin.user.service;

import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;


public class UserConditionBuilder {

	public static Condition listCondition() {

		SimpleCondition condition = new SimpleCondition();
		
		condition.orderDesc("id");
		
		return condition;
	}
}
