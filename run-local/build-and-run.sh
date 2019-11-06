#!/usr/bin/env bash

cd ..

./gradlew clean bootJar

cd run-local

mv ../build/libs/happy-solver-server.jar happy-solver-server.jar 

java -jar happy-solver-server.jar
