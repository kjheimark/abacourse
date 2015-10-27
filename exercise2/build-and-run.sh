#!/usr/bin/env bash
cd backend
mvn clean install
cd ..
docker-compose build
docker-compose up
