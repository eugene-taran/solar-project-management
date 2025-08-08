# Solar Project Management API Documentation

## Base URL
```
http://localhost:8080/api
```

## Endpoints

### Projects Resource

#### 1. Get All Projects
```
GET /projects
```

**Response:**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Smith Residence Solar Installation",
    "description": "10kW residential solar system",
    "customerName": "John Smith",
    "customerEmail": "john.smith@example.com",
    "customerPhone": "+1234567890",
    "installationAddress": "123 Main St, City, State 12345",
    "status": "PLANNING",
    "systemSizeKw": 10.5,
    "estimatedCost": 25000.00,
    "scheduledDate": "2025-03-15",
    "completionDate": null,
    "createdAt": "2025-01-08T10:30:00",
    "updatedAt": "2025-01-08T10:30:00"
  }
]
```

#### 2. Get Project by ID
```
GET /projects/{id}
```

**Response:** Same as single project object above

**Error Response (404):**
```json
{
  "status": 404,
  "message": "Project not found with id: 550e8400-e29b-41d4-a716-446655440000",
  "timestamp": "2025-01-08T10:30:00"
}
```

#### 3. Create Project
```
POST /projects
```

**Request Body:**
```json
{
  "name": "Smith Residence Solar Installation",
  "description": "10kW residential solar system",
  "customerName": "John Smith",
  "customerEmail": "john.smith@example.com",
  "customerPhone": "+1234567890",
  "installationAddress": "123 Main St, City, State 12345",
  "systemSizeKw": 10.5,
  "estimatedCost": 25000.00,
  "scheduledDate": "2025-03-15"
}
```

**Response (201 Created):** Returns created project object

**Validation Error Response (400):**
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "Project name is required",
    "customerEmail": "Invalid email format",
    "systemSizeKw": "System size must be greater than 0"
  },
  "timestamp": "2025-01-08T10:30:00"
}
```

#### 4. Update Project
```
PUT /projects/{id}
```

**Request Body:** Same as Create Project

**Response:** Returns updated project object

#### 5. Delete Project
```
DELETE /projects/{id}
```

**Response (204 No Content):** Empty response on success

## Project Status Values

- `PLANNING` - Initial planning phase
- `APPROVED` - Project approved by customer
- `IN_PROGRESS` - General in-progress status
- `INSTALLATION_SCHEDULED` - Installation date scheduled
- `INSTALLATION_IN_PROGRESS` - Currently installing
- `COMPLETED` - Project completed
- `CANCELLED` - Project cancelled
- `ON_HOLD` - Project on hold

## Validation Rules

### Project Creation/Update
- `name`: Required, max 255 characters
- `customerName`: Required, max 255 characters
- `customerEmail`: Optional, must be valid email format
- `customerPhone`: Optional, must match international phone format
- `installationAddress`: Required, max 500 characters
- `systemSizeKw`: Optional, must be greater than 0, max 8 digits with 2 decimal places
- `estimatedCost`: Optional, must be greater than 0, max 8 digits with 2 decimal places
- `scheduledDate`: Optional, must be a future date

## Error Handling

All errors follow a consistent format:
```json
{
  "status": "<HTTP_STATUS_CODE>",
  "message": "<ERROR_MESSAGE>",
  "timestamp": "<ISO_8601_TIMESTAMP>"
}
```

For validation errors, an additional `errors` field contains field-specific error messages.
