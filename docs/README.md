# Project documentation

Project is initialized with next spring initializr settings

![Spring Initializr](spring-initializr.png)

# System architecture

There we have an examples (not implemented, on which we can iterate)

```mermaid
graph TB
    subgraph "Frontend"
        UI[React UI]
    end
    
    subgraph "Backend"
        API[REST API]
        BL[Business Logic]
        DB[(PostgreSQL)]
    end
    
    subgraph "External Services"
        ES[Email Service]
        WS[Weather Service]
    end
    
    UI -->|HTTP/REST| API
    API --> BL
    BL --> DB
    BL --> ES
    BL --> WS
    
    style UI fill:#61dafb
    style API fill:#6db33f
    style BL fill:#6db33f
    style DB fill:#336791
```

# User flow

```mermaid
flowchart TD
    A[User Login] --> B{Authenticated?}
    B -->|No| C[Show Login Form]
    C --> A
    B -->|Yes| D[Dashboard]
    
    D --> E[View Projects]
    D --> F[Create New Project]
    D --> G[Reports]
    
    F --> H[Enter Project Details]
    H --> I[Add Solar Panels]
    I --> J[Schedule Installation]
    J --> K[Assign Team]
    K --> L[Save Project]
    L --> D
    
    E --> M[Select Project]
    M --> N{Project Actions}
    N --> O[Edit Details]
    N --> P[View Progress]
    N --> Q[Generate Invoice]
    N --> R[Complete Project]
    
    O --> L
    R --> D
```

# Request Flow

```mermaid
sequenceDiagram
    participant User
    participant Browser
    participant ReactUI as React UI
    participant API as REST API
    participant Auth as Auth Service
    participant BL as Business Logic
    participant DB as PostgreSQL
    participant Cache as Cache Layer
    participant ES as External Service
    
    User->>Browser: Click "Get Solar Report"
    Browser->>ReactUI: Handle Click Event
    ReactUI->>API: POST /api/projects/{id}/report
    API->>Auth: Validate JWT Token
    Auth-->>API: Token Valid
    API->>BL: generateReport(projectId)
    BL->>Cache: Check Cache
    Cache-->>BL: Cache Miss
    BL->>DB: SELECT project data
    DB-->>BL: Project Details
    BL->>DB: SELECT installation data
    DB-->>BL: Installation Records
    BL->>ES: Get Weather Data
    ES-->>BL: Weather Forecast
    BL->>BL: Calculate Solar Efficiency
    BL->>Cache: Store Result
    BL-->>API: Report Data
    API-->>ReactUI: JSON Response
    ReactUI-->>Browser: Render Report
    Browser-->>User: Display Solar Report
```
