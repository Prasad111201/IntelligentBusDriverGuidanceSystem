# Intelligent Bus Driver Guidance System

## Overview
The Intelligent Bus Driver Guidance System is a Java Maven application designed to manage buses and drivers while enforcing business validation rules. The project follows Software Engineering best practices including layered architecture, unit testing, integration testing, and continuous integration using GitHub Actions.

## Features

### Driver Management
- Add new drivers
- Update driver information
- Validate driver ID
- Validate licence types
- Check experience requirements
- Verify immutable fields

### Bus Management
- Add buses
- Update buses
- Validate bus IDs
- Validate capacity changes
- Verify fuel type restrictions

### Validation Rules
- Driver ID format validation
- Bus ID format validation
- Driver age restrictions
- Electric bus experience requirements
- Licence and fuel compatibility checks

## Technologies
- Java 21
- Maven
- JUnit 5
- GitHub Actions
- VS Code

## Project Structure

src/
├── main/java/com/busguidance
│ ├── model
│ ├── repository
│ ├── service
│ └── validation
│
└── test/java/com/busguidance

## Running Tests

mvn test

## Build Project

mvn clean package

## CI/CD

GitHub Actions automatically runs all tests on every push to the repository.

## Author

Prasad