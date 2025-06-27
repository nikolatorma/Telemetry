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

## Build and Run
1. Run docker
2. Execute build-and-run.sh which builds maven app, runs dockers containers for mysql db and spring app and does tests

## 📦 Technology Choices & Reasoning

### 📌 Why Spring Boot?

- **Built-in TCP/UDP support via Netty or custom socket implementations**  
  Spring Boot allows easy integration with Netty for building non-blocking TCP servers or simple multithreaded socket servers.

- **Excellent ORM support (JPA/Hibernate)**  
  Simplifies working with relational databases via declarative entity mappings and repository patterns.

- **Easy database integration**  
  Works seamlessly with MySQL and PostgreSQL.

- **Scalability and security**  
  Enterprise-grade frameworks like Spring Security ensure secure, scalable applications with minimal configuration.

- **Metrics and monitoring**  
  Native support for Micrometer enables collecting application metrics and integrating with observability tools like Prometheus and Grafana.

## 📡 Data Reception Server Responsibilities

- Listen on a TCP port
- Receive HEX telemetry messages as string data
- Parse messages using a dedicated `VehicleMessageParser`
- Update the current state of the vehicle in the database
- Record all telemetry messages into a historical log table

## ⚙️ Key Technical Considerations

- **Concurrency Management**  
  Use a thread pool or Netty's event loop model for efficient handling of multiple concurrent connections.

- **Security**  
  Restrict allowed IP addresses or networks; optionally secure connections with SSL/TLS.

- **Memory Management**  
  Pool parser instances and properly manage socket connections to avoid resource leaks.

- **Performance Optimization**  
  Apply batch inserts when persisting large volumes of historical telemetry messages to improve database write performance.

- **Auditing and Logging**  
  Persist raw incoming messages for troubleshooting and forensic analysis.

- **Message Integrity Validation**  
  Optionally use checksums or unique message identifiers to detect message corruption or duplication.

## 🖥️ State Management (Single Page JS App without a Framework)

In case of a frontend built in vanilla JavaScript (without React, Angular, or Vue), a clean state management solution would be:

### 🔹 Approach: Global Event Bus + State Singleton Pattern

A central global object maintains application state and allows components (forms, modules) to subscribe to changes and update the state.

- **Easy maintainability**
→ All state lives in one object; easier to track and debug.

- **Reactive UI behavior**
→ When state changes, subscribed components get notified and can update their views.

- **Isolated central store**
→ No scattered state logic; everything flows through one centralized manager.

- **What components can do**

→ Subscribe to AppState.subscribe(callback)

→ Modify state via AppState.setState({...})

→ Reactively update their UI based on state changes
