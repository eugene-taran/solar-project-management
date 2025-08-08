Let# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with the backend code in this repository.

## Backend-Specific Development

### Spring Modulith Architecture

This project uses Spring Modulith to create a modular monolith. When implementing features:

1. **Module Structure**: Each business domain should be its own module under `de.deveugene.solar`
   - Example: `de.deveugene.solar.projects`, `de.deveugene.solar.installations`, `de.deveugene.solar.reports`

2. **Module Boundaries**: Modules should communicate through:
   - Public APIs (interfaces in the module's root package)
   - Domain events (for asynchronous communication)
   - Never directly access another module's internal packages

### Package Organization

Within each module, follow this structure:
```
de.deveugene.solar.<module>/
├── <ModuleName>Module.java       # Module configuration
├── api/                          # Public APIs/DTOs
├── domain/                       # Domain entities and value objects
├── service/                      # Business logic
├── repository/                   # Data access
└── internal/                     # Module-internal implementations
```

### Database Conventions

1. **Entity Naming**: Use singular names (e.g., `Project`, not `Projects`)
2. **Table Naming**: PostgreSQL tables should use snake_case
3. **ID Generation**: Use UUID or sequences for primary keys
4. **Audit Fields**: Include `createdAt`, `updatedAt`, `createdBy`, `updatedBy` in entities

### API Design

1. **REST Endpoints**: Follow RESTful conventions
   - `GET /api/projects` - List all projects
   - `GET /api/projects/{id}` - Get specific project
   - `POST /api/projects` - Create project
   - `PUT /api/projects/{id}` - Update project
   - `DELETE /api/projects/{id}` - Delete project

2. **Response Format**: Use consistent response wrappers
3. **Error Handling**: Implement global exception handling with proper HTTP status codes

### Testing Strategy

1. **Unit Tests**: Test individual components in isolation
2. **Module Tests**: Use Spring Modulith's `@ModulithTest` for module integration tests
3. **Integration Tests**: Use `@SpringBootTest` sparingly for full application tests

### Common Gradle Tasks

```bash
# Run only unit tests
./gradlew test --tests "*Test"

# Run module tests
./gradlew test --tests "*ModuleTest"

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'

# Generate dependency report
./gradlew dependencies

# Check for outdated dependencies
./gradlew dependencyUpdates
```

### Configuration

1. **Application Properties**: 
   - `application.properties` - Default configuration
   - `application-dev.properties` - Development environment
   - `application-prod.properties` - Production environment

2. **Database Configuration**: PostgreSQL connection details should be externalized
3. **Module Configuration**: Each module should have its own `@Configuration` class

### Security Considerations

1. **Authentication**: Plan for JWT-based authentication
2. **Authorization**: Use Spring Security with method-level security
3. **Data Validation**: Always validate input data using Bean Validation
4. **SQL Injection**: Use parameterized queries (JPA handles this)
