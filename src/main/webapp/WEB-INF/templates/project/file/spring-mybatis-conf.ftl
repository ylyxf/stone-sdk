${project.projectPath}/src/main/resources/spring-mybatis-conf.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.1.xsd" >
	
	<!-- 本地数据库 -->
	<!--myBatis sqlSession Factory-->
  	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">  
    	<property name="dataSource" ref="${'$'}{dataSource.name}"></property> 
    	<property name="mapperLocations"  value="classpath*:**/*Mapper.xml"/>
    	<property name="configLocation"  value="classpath:mybatis-conf.xml"/>
    	<property name="plugins">
    		<array>
    			<ref bean="paginationInterceptor"/>
    		</array>
    	</property>
	</bean>
	
	<!-- plugin go pagination -->
	<bean id="paginationInterceptor" class="org.siqisource.stone.orm.dialect.PaginationInterceptor">
		<property name="databaseType" value="${'$'}{dataSource.databaseType}"/>
	</bean>
	
	<!-- scan Mapper Interfaces for generate Mapper Classes  -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer"> 
		<property name="basePackage" value="org.siqisource.stone,,${project.packageName}" /> 
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
		<property name="annotationClass" value="org.springframework.stereotype.Repository" />
	</bean>
	
</beans>