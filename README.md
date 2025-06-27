# Telemetry Server - Vehicle Data Processing

## Description

Telemetry Server is a backend service that processes telemetry data from vehicles in real-time. The service listens for incoming data from telematics devices connected to vehicles, parses the received messages, stores vehicle status information in a relational database, and exposes the data through REST APIs (optional). 

This project is built using **Spring Boot** and designed to handle incoming vehicle telemetry data in **hexadecimal format**, parse the data into a usable format, and store it for later processing.

## Features

- **Real-Time Data Processing**: Receives vehicle telemetry messages over TCP socket and processes them immediately.
- **Data Storage**: Vehicle and driver data are stored in a PostgreSQL database (or MySQL).
- **Spring Boot Framework**: Utilizes the power of Spring Boot for creating microservices and web applications.
- **Data Parsing**: Hexadecimal vehicle data is parsed into structured objects.
- **JUnit Tests**: Includes unit and dynamic tests to validate message parsing and database interaction.
- **Socket Server**: Receives telemetry data from vehicles on a dedicated TCP port.

## Prerequisites

- **Java 21**
- **Spring Boot 3.5.3**
- **MySQL**
- **Maven** for building the project
- **Eclipse IDE**

