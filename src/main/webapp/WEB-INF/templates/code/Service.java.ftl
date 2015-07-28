package ${project.basicPackage}.${table.modulePackageName}.service;

import ${project.basicPackage}.${table.modulePackageName}.mapper.${table.entity}Mapper;
import ${project.basicPackage}.${table.modulePackageName}.model.${table.entity};
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