${project.projectPath}/src/main/resources/config.properties
dataSource.databaseType = ${project.dbType}

#dataSourceName : innerDataSource or containerDataSource
dataSource.name = innerDataSource

#containerDataSource
containerDataSource.jndi = java:comp/env/jdbc/${project.name}

#innerDataSource
innerDataSource.driver = ${project.dbDriver}
innerDataSource.url = ${project.dbUrl}
innerDataSource.username = ${project.dbUser}
innerDataSource.password = ${project.dbPassword}
innerDataSource.minPoolSize = 15
innerDataSource.maxPoolSize = 50
innerDataSource.timeout = 600
innerDataSource.max_statement = 50
innerDataSource.testConnectionOnCheckout = true

#security
security.globalSessionTimeout = 1800

