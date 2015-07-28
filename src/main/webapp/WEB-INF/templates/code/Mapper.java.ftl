package ${project.basicPackage}.${table.modulePackageName}.mapper;

import org.siqisource.stone.orm.MybatisMapper;
import ${project.basicPackage}.${table.modulePackageName}.model.${table.entity};
import org.springframework.stereotype.Repository;

@Repository
public interface  ${table.entity}Mapper extends MybatisMapper<${table.entity}>{

}