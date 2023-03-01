# SPRING BOOT BATCH API FOR ADDING BOOK DETAILS

This is a Spring Boot Batch API for adding book details to MySQL DB. 



## Softwares Need

* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Spring framework tool to develop microservices and applications
* 	[Spring Tool Suite IDE](https://spring.io/tools) - IDE to develop the SpringBoot App
* 	[Maven](https://maven.apache.org/) - Build and Dependency management tool
* 	[MySQL](https://downloads.mysql.com/archives/community) - Relational Database Management System
* 	[Postman](https://www.getpostman.com/) - API testing tool



## packages

- `controllers` — Rest API Client listener
- `model` — to hold our bean class
- `repository` — to write implementations of service interfaces
- `application.properties` - Spring reads the properties defined in this file to configure the application
- `pom.xml` - contains all the project dependencies


### Dependencies

Spring Boot starter pack is used for bootstrapping the main class

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-batch</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```


- Create MySQL Table
```
CREATE TABLE `test`.`BOOK_DETAILS` (
  `ISBN_ID` INT NOT NULL,
  `BOOK_NAME` VARCHAR(45) NULL,
  `AUTHOR_NAME` VARCHAR(45) NULL,
  `PUBLICATION_NAME` VARCHAR(45) NULL,
  `YEAR_PUBLISHED` INT NULL,
  PRIMARY KEY (`ISBN_ID`));
  
```


- MySql Configuration

```properties
server.port = 8989
server.servlet.context-path=/training/dboperation
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=*******
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

```
## How to Run this

- Checkout the SPRING BOOT BATCH API FOR ADDING BOOK DETAILS code from GIT
- Import into Spring Tool Suite as Maven project
- Run as Maven project with below goals

```shell
clean install spring-boot:run [For STS]
mvn clean install spring-boot:run [For Command prompt]
```


### Endpoints

Below are the endpoints to test your API

|  URL |  Method | Header| Purpose|
|----------|--------------|--------------|--------------|
|`http://localhost:8989/training/dboperation/api/bookDetails`                           | GET | `Content-Type: text/plain`| Get the Book Details|
|`http://localhost:8989/training/dboperation/api/bookDetails`                           | POST | `Content-Type: text/plain`| Insert the Book Details|
|`http://localhost:8989/training/dboperation/api/bookDetails/{isbn_id}`                 | PUT | `Content-Type: text/plain`| Update the Book Details|
|`http://localhost:8989/training/dboperation/api/bookDetails/{isbn_id}`                 | DELETE | `Content-Type: text/plain`| Delete the Book Details|
|`http://localhost:8989/training/dboperation/api/bookDetails`                           | DELETE | `Content-Type: text/plain`| Delete all the Book Details|


## How to Test

- Launch the Postman App
- In the Request window, 
     - Select the POST method
     - Paste the endpoint URL
     - Enter test data in the Request Body
- Press the "Send" button and check the Request body for output
