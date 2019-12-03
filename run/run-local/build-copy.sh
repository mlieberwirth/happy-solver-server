#!/usr/bin/env bash
set -euxo pipefail

cd ../..

./gradlew clean bootJar

cd run/run-local/

cp ../../build/libs/happy-solver-server.jar happy-solver-server.jar 

