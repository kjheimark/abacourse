#!/usr/bin/env bash

JAR=$(ls *.jar)

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=47000 \
    -jar ${JAR}
