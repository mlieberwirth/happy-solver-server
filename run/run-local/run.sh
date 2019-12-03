#!/usr/bin/env bash

java -Dspring.cloud.config.profile=local \
     -Dspring.profiles.active=local \
     -Xmx512M \
     -Xms512M \
     -jar happy-solver-server.jar
