package org.siqisource.stone.sdk.project.service;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.sdk.project.controller.ProjectQueryForm;

public class ProjectConditionBuilder {

	public static Condition listCondition(ProjectQueryForm projectQueryForm) {

		SimpleCondition condition = new SimpleCondition();

		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		 
		condition.orderAsc("id");
		return condition;
	}
}

