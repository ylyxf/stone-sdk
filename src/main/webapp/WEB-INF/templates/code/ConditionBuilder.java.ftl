package ${project.basicPackage}.${table.modulePackageName}.controller;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import ${project.basicPackage}.${table.modulePackageName}.controller.${table.entity}QueryForm;

public class ${table.entity}ConditionBuilder {

	public static Condition listCondition(${table.entity}QueryForm ${table.lowerEntity}QueryForm) {

		SimpleCondition condition = new SimpleCondition();

		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		 
		return condition;
	}
}
