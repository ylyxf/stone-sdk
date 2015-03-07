${project.projectPath}/src/main/java/${project.packagePath}/${table.modulePath}/controller/${table.entity}Controller.java
package ${project.packageName}.${table.modulePackageName}.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ${table.entity}Controller {


	@RequestMapping("/${table.modulePackageName}/${table.lowerEntity}/List.do")
	public String list( Model model) {
		return "${table.modulePackageName}/${table.lowerEntity}List";
	}


}
