package com.siqisoft.stone.admin.group.service;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;

import com.siqisoft.stone.admin.group.controller.GroupQueryForm;

public class GroupConditionBuilder {

	public static Condition listCondition(GroupQueryForm groupQueryForm) {

		SimpleCondition condition = new SimpleCondition();
		Integer id = groupQueryForm.getId();
		id = id == null ? -1 : id;
		condition.andEqual("parentId", id);
		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		condition.orderAsc("sortNo");
		return condition;
	}
}
