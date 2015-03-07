${project.projectPath}/src/main/resources/application-context.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.1.xsd
		http://www.springframework.org/schema/mvc    
		http://www.springframework.org/schema/mvc/spring-mvc-3.1.xsd
		http://www.springframework.org/schema/aop  
            http://www.springframework.org/schema/aop/spring-aop-3.1.xsd">

	<context:property-placeholder
		ignore-resource-not-found="true" location="classpath:config.properties"
		ignore-unresolvable="true" />

	<context:component-scan base-package="org.siqisource.stone,${project.packageName}">
		<context:exclude-filter type="annotation"
			expression="org.springframework.stereotype.Controller" />
	</context:component-scan>

	<aop:config expose-proxy="true" />

	<!-- datasource config -->
	<import resource="spring-datasource-conf.xml" />

	<!-- mybatis config -->
	<import resource="spring-mybatis-conf.xml" />

	<!-- security config -->
	<import resource="spring-security-conf.xml" />

</beans>