#!/bin/bash

# Build Spring Boot app
echo "Building Spring Boot app with Maven..."
./mvnw clean package -DskipTests

# Run Docker Compose (build + start services)
echo "Starting MySQL and Spring Boot app via Docker Compose..."
docker compose -f docker-compose.yml up --build -d

# Run tests after containers are up
echo "Running integration and unit tests..."
./mvnw test

# Tail the logs to keep the terminal running
docker logs -f telemetry-app
