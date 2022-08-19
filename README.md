# Account Statement Manager

The application can be used to view account statements of different users from the MS Access database. The Rest API 
based application is developed using Java 17 and Spring framework. 

### Implemented Features
* Implemented role based authorization using spring security.
* The user cannot log in twice. User should log out before login from another browser.
* The session timeout is configured as 5 minutes.
* Implemented application logging using Spring Aspect and Slf4J.
* Implemented error handling using ControllerAdvice.
* For user's with USER role, API always return statements for last three months.

### Java Libraries 
* Spring Boot 2.7.2
* JDBC Template
* Spring Security
* Maven
* Junit 5
* Mockito
* Slf4j
* Lombok
* Swagger
* Jacoco
* SonarQube

### Instructions to build and start the application.
Ensure that you are in the project root directory.
#### Build the application using any of the below maven command.

* Build application and run unit test.

  `mvn clean install`
* Build application by skipping the unit tests.

  `mvn clean install -DskipTests`

* Prepare Jacoco reports & add to sonar

  `mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install`

  `mvn sonar:sonar -Dsonar.login={sonarqube-token}`

#### Start the application using any of the below command.
* Start using maven command,  the database path should be provided in the run command as a run argument as below.

  `mvn spring-boot:run -Dspring-boot.run.arguments=--database.path=path/to/accountsdb.accdb`
  
  eg: `mvn spring-boot:run -Dspring-boot.run.arguments=--database.path=C://Users/91755/Documents/Sujith/accountsdb.accdb`

* The default port for the application is configured as `8080`. It can be overridden using below command.

  `mvn spring-boot:run -Dspring-boot.run.arguments="--database.path=path/to/accountsdb.accdb --port=8085"`


### Users and Roles
Only two users are available for the application

| Username | Password | Role  |
|----------|----------|-------|
| user     | user     | USER  |
| admin    | admin    | ADMIN |
### Application Rest APIs
#### http://localhost:8080/statements/{accountId}?fromDate={fromDate}&toDate={toDate}&fromAmount={fromAmount}&toAmount={toAmount}
* Access to this API is restricted to `ADMIN` users only.
* `accountId` is required parameter and added as a path parameter. The value should be a number. eg: `1`,`4`
* `fromDate` and `toDate` are optional request parameters. The value should be in `MMDDYYYY` format. eg: `07152022`
* `fromAmount` and `toAmount` are optional request parameters. The value should be in number format. eg: `10`,`180.24`

#### http://localhost:8080/statements/user/{accountId}
* This API can be accessed by users with `ADMIN` role or `USER` role.
* `accountId` is a required parameter and added as a path parameter. The value should be a number. eg:`1`,`4`

