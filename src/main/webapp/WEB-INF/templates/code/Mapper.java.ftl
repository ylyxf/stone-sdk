${project.projectPath}/src/main/java/${project.packagePath}/${table.modulePath}/mapper/${table.entity}Mapper.java
package ${project.packageName}.${table.modulePackageName}.mapper;

import org.siqisource.stone.orm.MybatisMapper;
import ${project.packageName}.${table.modulePackageName}.model.${table.entity};
import org.springframework.stereotype.Repository;

@Repository
public interface ${table.entity}Mapper extends MybatisMapper<${table.entity}>{

}