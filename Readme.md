# Happy-Solver-Server

Runs the [Happy-Solver](https://github.com/mlieberwirth/happy-solver) in a server with a database to store models and results.
Current integrated optimizations:
 - BinPacking 

## Preconditions
You need Java 8, gradle and optional docker with docker-compose for the database. 

## Run Server

### Start a database

If you have docker and docker-compose installed you can simply use the docker-compose.yml in folder run-local

    docker-compose -f ./run-local/docker-postgresql.yml up -d
     
or you need to provide a database and configure the relevant application.yml.

### Run in IDE

You only need to compile the sources as gradle-project in for instance eclipse and run ServerLauncher.java as application. Relevant application.yml is under 'src/main/resources'

### Run with gradle

    ./gradlew bootRun

### Create and run jar 
For build a jar use gradle

    ./gradlew bootJar
    
after that a executable jar is under /build/libs/. Run it with
    
    java -jar ./build/libs/happy-solver-server.jar

### With bash

Execute ``./build-copy.sh && ./run.sh`` in folder run-local. Relevant application.yml is in same folder.

## RESTful Web Service endpoints

The REST-Api is documented by swagger2. If the server is running on localhost with port 8080 it is available under

    http://localhost:8080/swagger-ui.html


# TODO

## Finish production
- service for create model
- controller for create model
- create BinSolution 
- add happy-solver

## Test
- Write MVC-Tests
- Write JUnit tests
