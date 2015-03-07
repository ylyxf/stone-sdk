${project.projectPath}/pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>${project.packageName}</groupId>
	<artifactId>${project.name}</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>${project.name} Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<sourceVersion>1.6</sourceVersion>
		<targetVersion>1.6</targetVersion>

		<!-- Build Dependency Version Properties -->
		<stone.version>${project.stoneVersion}</stone.version>
		<javaee-web-api.version>6.0</javaee-web-api.version>
		<jstl.version>1.1.2</jstl.version>
		<standard.version>1.1.2</standard.version>

		<!-- Plugin Dependency Version Properties -->
		<maven-war-plugin.version>2.2</maven-war-plugin.version>
		<maven-source-plugin.version>2.2.1</maven-source-plugin.version>
		<maven-compiler-plugin.version>2.3.2</maven-compiler-plugin.version>
		<tomcat7-maven-plugin.version>2.0</tomcat7-maven-plugin.version>


	</properties>

	<dependencies>

		<dependency>
			<groupId>org.siqisource</groupId>
			<artifactId>stone</artifactId>
			<#noparse><version>${stone.version}</version></#noparse>
		</dependency>

		<#switch  project.dbDriver >
   			<#case "org.postgresql.Driver"> 
				<dependency>
					<groupId>postgresql</groupId>
					<artifactId>postgresql</artifactId>
					<version>9.1-901.jdbc4</version>
				</dependency>
			<#break>   
		</#switch>


		<!-- #############jee################# -->
		<dependency>
			<groupId>javax</groupId>
			<artifactId>javaee-web-api</artifactId>
			<#noparse><version>${javaee-web-api.version}</version></#noparse>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<#noparse><version>${jstl.version}</version></#noparse>
		</dependency>
		<dependency>
			<groupId>taglibs</groupId>
			<artifactId>standard</artifactId>
			<#noparse><version>${standard.version}</version></#noparse>
		</dependency>

	</dependencies>

	<build>
		<resources>
			<resource>
				<directory>src/main/java</directory>
				<includes>
					<include>**/*.xml</include>
				</includes>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
			</resource>
		</resources>

		<plugins>
			<!-- Compiler -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<#noparse><version>${maven-compiler-plugin.version}</version></#noparse>
				<configuration>
					<#noparse><encoding>${sourceEncoding}</encoding></#noparse>
					<#noparse><source>${sourceVersion}</source></#noparse>
					<#noparse><target>${targetVersion}</target></#noparse>
				</configuration>
			</plugin>

			<!-- source -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-source-plugin</artifactId>
				<#noparse><version>${maven-source-plugin.version}</version></#noparse>
			</plugin>

			<!-- Build War -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<#noparse><version>${maven-war-plugin.version}</version></#noparse>
			</plugin>

			<!-- Tomcat7 -->
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<#noparse><version>${tomcat7-maven-plugin.version}</version></#noparse>
				<configuration>
					<port>2234</port>
					<path>/${project.name}</path>
					<uriEncoding>UTF-8</uriEncoding>
				</configuration>
			</plugin>
		</plugins>
	</build>
	 
</project>
