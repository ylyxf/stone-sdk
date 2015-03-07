${project.projectPath}/src/main/java/${project.packagePath}/${table.modulePath}/service/${table.entity}Service.java
package ${project.packageName}.${table.modulePackageName}.service;

import ${project.packageName}.${table.modulePackageName}.mapper.${table.entity}Mapper;
import ${project.packageName}.${table.modulePackageName}.model.${table.entity};
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${table.entity}Service extends AbstractService<${table.entity}>   {

	@Autowired
	${table.entity}Mapper mapper;

	@Override
	protected MybatisMapper<${table.entity}> getMapper() {
		return this.mapper;
	}
}	