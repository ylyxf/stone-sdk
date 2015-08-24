<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${project.basicPackage}.${table.modulePackageName}.mapper.${table.entity}Mapper">
<#if !table.databaseName?contains("oracle")>
<insert id="insert" parameterType="${project.basicPackage}.${table.modulePackageName}.model.${table.entity}" 
<#if  table.autoGenerateKey> useGeneratedKeys="true" <#list table.primaryColumns as column><#if column.autoIncrement> keyProperty="${column.name}"</#if></#list></#if> >
INSERT INTO ${table.name} (
	<#list table.commonColumns as column>
	${column.name}<#if  column_has_next>,</#if>
	</#list> 
) VALUES (
	<#list table.commonColumns as column>
	${r"#"}{${column.property} ,jdbcType=${column.jdbcType} }<#if  column_has_next>,</#if>
	</#list>
)
</insert>
</#if>
<#if table.databaseName?contains("oracle")>
<insert id="insert" parameterType="${project.basicPackage}.${table.modulePackageName}.model.${table.entity}">
	<selectKey resultType="java.math.BigDecimal" order="BEFORE" keyProperty="id">  
       SELECT SEQ_${table.name}_id.Nextval as ID from DUAL  
   </selectKey>
   INSERT INTO ${table.name} (
	<#list table.columns as column>
	${column.name}<#if  column_has_next>,</#if>
	</#list> 
	) VALUES (
		<#list table.columns as column>
		${r"#"}{${column.property} ,jdbcType=${column.jdbcType} }<#if  column_has_next>,</#if>
		</#list>
	)
</insert>
</#if>

<insert id="insertPartitive" parameterType="map">
	INSERT INTO ${table.name}  
		(${r"${fields._insertFields}"}) 
	VALUES 
		(${r"${fields._insertValues}"})
</insert>

<select id="read" parameterType="map" resultType="${project.basicPackage}.${table.modulePackageName}.model.${table.entity}">
	SELECT * FROM  ${table.name} WHERE
	<#list table.primaryColumns as column>
	${column.name} = ${r"#"}{id[${column_index}]} <#if  column_has_next> and </#if>
	</#list>
</select>
	  
<select id="count" parameterType="map" resultType="int">
	SELECT count(1) FROM  ${table.name}  
	<if test="condition.expressions != null ">
		<where>${r"${condition.comboedExpressions}"}</where>
	</if>		
</select>
	  
<select id="list" parameterType="map" resultType="${project.basicPackage}.${table.modulePackageName}.model.${table.entity}">
	SELECT * FROM  ${table.name} 
	<if test="condition.expressions != null ">
		<where>${r"${condition.comboedExpressions}"}</where>
	</if>		
	<if test="condition.orderBy != null">
		${r"${condition.orderBy}"}
	</if>  
</select>

<update id="update" parameterType="${project.basicPackage}.${table.modulePackageName}.model.${table.entity}">
	UPDATE ${table.name} SET 
	 	<#list table.commonColumns as column> 
	 	${column.name}= ${r"#"}{${column.property} ,jdbcType=${column.jdbcType} }<#if  column_has_next>,</#if>
		</#list>
	WHERE 
	<#list table.primaryColumns as column>
	${column.name} = ${r"#"}{${column.property}} <#if  column_has_next> and </#if>
	</#list>
</update>
	
<update id="updatePartitive" parameterType="map" >
	UPDATE  ${table.name}
	SET ${r"${fields._updateSql}"}
	WHERE 
	<#list table.primaryColumns as column>
	${column.name} = ${r"#"}{id[${column_index}]} <#if  column_has_next> and </#if>
	</#list>
</update>
	  
<update id="updateBatch" parameterType="map" >
	UPDATE  ${table.name} 
	SET  ${r"${fields._updateSql}"}
	<if test="condition.expressions != null ">
		<where> ${r"${condition.comboedExpressions}"} </where>
	</if>		
</update>

<delete id="delete" parameterType="map" >
	DELETE FROM  ${table.name}   
	WHERE 
	<#list table.primaryColumns as column>
	${column.name} = ${r"#"}{id[${column_index}]} <#if  column_has_next> and </#if>
	</#list>
</delete>
	
<delete id="deleteBatch" parameterType="map" >
	DELETE FROM ${table.name}
	<if test="condition.expressions != null ">
		<where>${r"${condition.comboedExpressions}"}</where>
	</if>		
</delete>
	
</mapper>