#!/usr/bin/env bash
set -euxo pipefail

cd ../..

./gradlew clean bootJar

cd run/docker/

cp ../../build/libs/happy-solver-server.jar happy-solver-server.jar 

docker build -t happy-solver-server:1.0.0 .
