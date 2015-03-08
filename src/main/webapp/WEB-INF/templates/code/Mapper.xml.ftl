${project.projectPath}/src/main/resources/mappers/${table.entity}Mapper.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${project.packageName}.${table.modulePackageName}.mapper.${table.entity}Mapper">

<insert id="insert" parameterType="${project.packageName}.${table.modulePackageName}.model.${table.entity}"  <#if table.autoGenerateKey>useGeneratedKeys="true" keyProperty="id"</#if>>
INSERT INTO ${table.name} (
<#list table.commonColumns as column>
	${column.name} <#if column_has_next>,</#if>
</#list>
) VALUES (
<#list table.commonColumns as column>
	${'#'}{${column.property} ,jdbcType=${column.jdbcType} }<#if column_has_next>,</#if>
</#list>
)
</insert>


<insert id="insertPartitive" parameterType="map">
	INSERT INTO ${table.name}  
		(${'$'}{fields._insertFields}) 
	VALUES 
		(${'$'}{fields._insertValues})
</insert>

<select id="read" parameterType="map" resultType="${project.packageName}.${table.modulePackageName}.model.${table.entity}">
	SELECT * FROM ${table.name}   	 
	WHERE
	<#list table.primaryColumns as column>
	${column.name} = ${'#'}{id[${column_index}]} <#if column_has_next> AND </#if>
	</#list>	
</select>
	  
<select id="count" parameterType="map" resultType="int">
	SELECT count(1) FROM  ${table.name}   
	<if test="condition.expressions != null ">
		<where>${'$'}{condition.comboedExpressions}</where>
	</if>		
</select>
	  
<select id="list" parameterType="map" resultType="${project.packageName}.${table.modulePackageName}.model.${table.entity}">
	SELECT * FROM  ${table.name}   
	<if test="condition.expressions != null ">
		<where>${'$'}{condition.comboedExpressions}</where>
	</if>		
	<if test="condition.orderBy != null">
		${'$'}{condition.orderBy}
	</if>  
</select>

<update id="update" parameterType="${project.packageName}.${table.modulePackageName}.model.${table.entity}">
	UPDATE ${table.name} SET 
	<#list table.commonColumns as column>
	 	${column.name} = ${'#'}{${column.property} ,jdbcType=${column.jdbcType} } <#if column_has_next>,</#if>
	</#list>
	WHERE 
	<#list table.primaryColumns as column>
	${column.name} = ${'#'}{id[${column_index}]} <#if column_has_next> AND </#if>
	</#list>				
</update>
	
<update id="updatePartitive" parameterType="map" >
	UPDATE  ${table.name} 
	SET ${'$'}{fields._updateSql}
	WHERE 
	<#list table.primaryColumns as column>
	${column.name} = ${'#'}{id[${column_index}]} <#if column_has_next> AND </#if>
	</#list>			
</update>
	  
<update id="updateBatch" parameterType="map" >
	UPDATE  ${table.name}   
	SET  ${'$'}{fields._updateSql}
	<if test="condition.expressions != null ">
		<where> ${'$'}{condition.comboedExpressions} </where>
	</if>		
</update>

<delete id="delete" parameterType="map" >
	DELETE FROM  ${table.name}   
	WHERE 
	<#list table.primaryColumns as column>
	${column.name} = ${'#'}{id[${column_index}]} <#if column_has_next> AND </#if>
	</#list>
</delete>
	
<delete id="deleteBatch" parameterType="map" >
	DELETE FROM ${table.name} 
	<if test="condition.expressions != null ">
		<where>${'$'}{condition.comboedExpressions}</where>
	</if>		
</delete>
	
</mapper>