# PTS - Parking Toll Service

Swagger : http://localhost:8080/swagger-ui.html

## What is PTS ?

PTS (or Parking Toll Service) is a service that can help the management of toll parkings with multiple types of slots. 

## Technical Environment

### Basic

The following technologies are used :
* Gradle 5+
* Spring Boot 2
* Java 11
* MySQL
* Docker
* JUnit 5 (Juniper API)

### In Detail

The Spring Boot 2 suite has been used to be able to provide APIs, Database connectivity using spring boot jpa packages. For MySQL, the version 5.7 is used in order to be AWS RDS-ready (while a possible upgrade to the version 8 is transparently possible).
Alongside, the Apache JDBC Pooler has been chosed instead of the default HikariCP one. The reason is that the Apache pooler provides a more versatile solution for multiple connections without having to manage the lifetime of the connections in the config, something that has to be done with HikariCP.
What is more, while HikariCP is faster for a number of queries, the difference between HikariCP and Apache is reduced to almost zero on a big number of simultaneous connections.

### Testing

For testing, apart from the classic spring boot starter libraries, we made use of an embedded HSQL database that resembles the real schema of the db we will have on production.
This enables us to mock the tests and to reproduce a behavior very similar to the one we will have on the production environment. 

## Functional Details

For every entity (see the db schema on the file pts_db.mwb), there is a basic CRUD API with a small twist.

Small compromise. For the moment the car type (for which we will define its slot type) is the same with the parkingSlotType, that's why it's passed with the checkIn API and it's not saved with the car inside the db. Check out the Improvements section for more info. 

We use GET for everything that involves get.
We use POST for everything that involves creation or/and edition. Meaning we use the same route for both creation and edition and we use the same interface to save or edit. The basic idea is that if the item posted doesn't exist, it will be created and if it does exist it will be updated.
If there is a bit more time, we could divide the creation and the edition by doing an idempotent PUT for creation and a POST for edition, while the only difference in the implementation will be that we will check the existence before the save. 
We use DELETE for everything that involves deletion. 

We use the basic HTTP codes, 200 OK for any successful call, exception with HTTP 500 for any failed call. HTTP 404 for any resource that doesn't exist.
Note : for objects that don't exist but the request is correct e.g. api/pts/car/5 while there is no such element in the db, we sent null with HTTP 200 OK status.
The philosophy behind that is that the call made by the user is correct, but the result is null. For an incorrect call/route, we would send 404 and for an exception/internal error we would send 500. 

## Build the sources

To be able to build the .war (with current version 0.0.1), you need to install Gradle.
Then, inside your terminal, type the following command. 

```$xslt
gradle clean build install
```

## Docker

We use a simple Dockerfile with tomcat 9, along with a docker compose that builds the whole app. 
Note : before invoking the docker-compose command, make sure you read the Gradle section.

```$xslt
docker-compose up
```  
The docker-compose will first create the MySQL database from the file pts_db.sql and when this is done, the API will start. 

## Improvements

### Technical

* Introducing logging (Apache log4j most possibly).
* Usage of liquibase for database migrations.
* Unit Tests with mockito, to co-exists along with the embedded db ones.
* Java 14
* Introduce basic exception handling hub using annotations and AspectJ.
* Introduce basic wrapping of the final object that is sent back from the API.
* Multithreading where it's due with a custom Completable Future to avoid memory leaks (check if fixed with Java 14).
* For versatility, cut the project into 3 packages, one for the repository-model layer, one for the business layer, one for the API layer.
* Introduce more DTOs, this way the abstraction is better and potentially we can foresee some potential problems.
* Introduce a NoSQL solution for the visiting system (CassandraDB could be good for that), along with a queue system (PubNub, AWS SQS) per Slot Type in order to be faster and safer.
* Right now the concurrency is resolved on a db level (locks), with AWS RDS this can be as good as the software locking, but for safety, we could introduce some semaphores for the slot allocation, we can synchronize the slot allocations by using messaging queueing systems such as PubNub or AWS SQS.
* Automate model tests, equals and toString with annotations in order to be implemented automatically using Apache Commons 3 library.  

### Functional

* Introduce Car Type, in case we have changes between which car type corresponds to which Slot Type. 
* Find a way to make Pricing Policy more generic, right now we need a bit of code to introduce a new Pricing Policy.
* Having the registry plates on the db is not the best thing in the world. We can use a technology like blockchain to identify each car about his visits in the garage. It would be a nice version 2.0.

