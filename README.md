----------------------
Install dependencies using 'mvn install'
----------------------
Execute tests from command line running 'mvn test'
-------------------------
Start the Restful api at localhost:8080 by running 'mvn spring-boot:run'
-------------------------
Test the api with using postman, or curl from the commandline
-------------------------
Access the in memory H2 Database from a browser at: http://localhost:8080/api/smart/h2-console
-------------------------

**Using curl to test the Restful Api**

curl -i -H "Accept:application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/smart/reads

curl -i -H "Accept:application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/smart/reads/1

curl -i -H "Accept:application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/smart/reads/2

**Access the in memory database from a broswer**

Navigate to: http://localhost:8080/api/smart/h2-console
Make sure:
JDBC URL is: jdbc:h2:mem:testdb
Username: sa

See attached screenshot "access-database.png" at the root of the project


**Assumptions about the Test**

Since the test requirement specified RESTful, the built Api includes also Hateoas


As there was not much explanation about the fields from the database, so I assumed they all belonged to a single table

Following REST standards, the requested endpoint "http://localhost:8080/api/smart/reads/{ACCOUNTNUMBER}
will return the smartread holding the id equal to the one comming in the path variable {ACCOUNTNUMBER},
which indicates that ACCOUNTNUMBER is the primary key of the entity.

For the /reads endpoint, the response for each smartread only returns two attributes: gas_read and elec_read.
The entire smartread with all its attributes are returned when fetching a specific smart read at /reads/{ACCOUNTNUMBER}


**The solution**

*Design of the solution*

The solution was designed using Java 8, maven, Spring Boot, and h2database as the in-memory database.
The chosen approach to solve this challenge was to keep it simple: We need a RESTful web service available to serve
samrt read from a database, and it is a blocker for other teams.
To achieve this as quickly as possible but with thoughts in quality and reliability, the solution was built using Spring boot data rest.
This Spring starter abstracts a lot of the initial dependency management boilerplate, allowing the team to spend the time 
on the real business logic of the project.
Under the hood, Spring data rest combines Spring MVC to build the Restful webservices, along with Spring Data repositories.
Additionally, Spring boot has by default an integrated Tomcat server, which provides an easy way for local testing.

*Tests*

As the core of the solution relies upon the components of Spring itself, the creation of unit tests held no meaning, at least for
the current requirement, which is quite simple: serve resources (smart reads) comming from a database.
So in order to test the application we have Integration tests, which validate the real application (without any mocking).
The tests perform requests to valid urls for the existing resources (for both reads and individual reads) as well as invalid resources,
and check on the responses.


-------------------------

application.properties file: Contains configuration properties for logging and to enable the h2 database
web console to check on the existing smart reads in the database.

data.sql file: Has the SQL code to insert the test data reads in the database every time it boots up

--------------

**Possible improvements**

Spring data rest starter provides an easy and straightforward way to serve DB data as restful resources. However, if the application
eventually needs to add more complex logic, it might be better to migrate to the traditional Spring MVC + Spring Data/JPA stack,
where we have more flexibility to build our own services and Dao layers.
