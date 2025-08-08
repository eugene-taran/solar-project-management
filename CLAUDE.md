# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Solar Project Management System - a web application for managing solar energy projects. The project uses a modular architecture with separate backend, frontend, and infrastructure components.

## Technology Stack

**Backend** (Spring Boot Application):
- Java 24 with Spring Boot 3.5.4
- Spring Modulith for modular architecture
- PostgreSQL database
- Gradle with Kotlin DSL build system

**Frontend**: React (planned, not yet implemented)

**Infrastructure**: Not yet implemented

## Common Commands

### Backend Development

From the `backend/` directory:

```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run tests
./gradlew test

# Run a specific test class
./gradlew test --tests "de.deveugene.solar.SomeTestClass"

# Clean build
./gradlew clean build

# Check for dependency updates
./gradlew dependencyUpdates
```

## Architecture

### Spring Modulith Structure

The backend uses Spring Modulith for creating a modular monolith. This allows for:
- Clear module boundaries within a single application
- Better code organization and encapsulation
- Easier transition to microservices if needed

### Package Structure

- Base package: `de.deveugene.solar`
- Module organization should follow Spring Modulith conventions
- Each module should have its own package with clear boundaries

### Database

- PostgreSQL is configured as the runtime database
- JPA/Hibernate for ORM
- Database migrations should be managed appropriately (consider Flyway/Liquibase)

## Development Notes

1. **Java Version**: The project uses Java 24, ensure your development environment supports this version

2. **Spring Boot Configuration**: Application properties are in `backend/src/main/resources/application.properties`

3. **Testing**: Tests use JUnit 5 with Spring Boot Test support. Integration tests should leverage Spring Modulith's testing capabilities

4. **Module Development**: When adding new features, consider creating them as separate Spring Modulith modules to maintain clean architecture boundaries