${project.projectPath}/src/main/java/${project.packagePath}/${table.modulePath}/model/${table.entity}.java
package ${project.packageName}.${table.modulePackageName}.model;

import java.util.Date;
			
public class ${table.entity} {

<#list table.columns as column>
	/*${column.comment}*/
	private  ${column.javaType} ${column.property};

</#list>
 
<#list table.columns as column>

	public  ${column.javaType} get${column.upperProperty}(){
		return this.${column.property};
	}
	 
	public  void set${column.upperProperty}(${column.javaType} ${column.property}){
		this.${column.property} = ${column.property} ;
	}

</#list>
	 
}