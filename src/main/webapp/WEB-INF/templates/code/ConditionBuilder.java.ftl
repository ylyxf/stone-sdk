${project.projectPath}/src/main/java/${project.packagePath}/${table.modulePath}/service/${table.entity}ConditionBuilder.java
package ${project.packageName}.${table.modulePackageName}.service;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import ${project.packageName}.${table.modulePackageName}.controller.${table.entity}QueryForm;

public class ${table.entity}ConditionBuilder {

	public static Condition listCondition(${table.entity}QueryForm ${table.lowerEntity}QueryForm) {

		SimpleCondition condition = new SimpleCondition();
		<#list table.columns as column>
	 	<#if column.name == "logic_deleted"> 
		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		</#if>
		</#list>
		
		<#list table.primaryColumns as column>
		condition.orderDesc("${column.property}");
		</#list> 
		
		return condition;
	}
}
