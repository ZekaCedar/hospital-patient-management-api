# Patient Management REST API

A RESTful API built with Spring Boot that handles basic patient
operations for a hospital information system.

## Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- Bean Validation (Jakarta Validation)

## Prerequisites

- JDK 17 or higher
- Maven
- MySQL 8.0+

## Setup & Installation

1. **Clone the repository**

```bash
git clone https://github.com/ZekaCedar/hospital-patient-management-api.git
cd hospital-patient-management-api
```

2. **Create MySQL database**

```sql
CREATE DATABASE db_bootest;
```

3. **Configure database connection**

Create `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_bootest
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

4. **Run the application**

```bash
mvn spring-boot:run
```

The API will start at `http://localhost:8081`

## API Endpoints

| Method | Endpoint                | Description                          |
|--------|-------------------------|--------------------------------------|
| POST   | /api/patients           | Create a new patient                 |
| GET    | /api/patients           | List all patients (with pagination)  |
| GET    | /api/patients/{id}      | Get a patient by ID                  |
| PUT    | /api/patients/{id}      | Update a patient                     |
| DELETE | /api/patients/{id}      | Soft delete a patient (set inactive) |

### Create Patient

**POST** `/api/patients`

Request body:

```json
{
    "firstName": "John",
    "lastName": "Doe",
    "dateOfBirth": "1985-05-15",
    "phoneNumber": "+1234567890",
    "email": "john.doe@email.com",
    "address": "123 Main St, City, State",
    "emergencyContact": "Jane Doe - +0987654321",
    "bloodType": "O_POSITIVE"
}
```

Success response: `201 Created`

### Get All Patients (Paginated)

**GET** `/api/patients?page=0&size=10`

Query parameters:

| Parameter | Default | Description                  |
| :---:     | :---:   | :---                         |
| page      | 0       | Page number (zero-based)     |
| size      | 10      | Number of records per page   |

Success response: `200 OK`

### Get Patient by ID

**GET** `/api/patients/{id}`

Success response: `200 OK`

### Update Patient

**PUT** `/api/patients/{id}`

Request body: same as Create Patient

Success response: `200 OK`

### Delete Patient (Soft Delete)

**DELETE** `/api/patients/{id}`

Sets `isActive` to `false`. The record is not removed from the database.

Success response: `200 OK`

