# Car Management API

This project is a Spring Boot application designed to manage car data, providing a RESTful API for CRUD operations.
Ensure the following tools are installed on your system:

- Java 17 or higher
- Maven 3.8 or higher
- MySQL database
- Postman or any REST client (optional for testing)
  
## Setup and Running the Application

### 1. Clone the Repository

```bash
git clone https://github.com/your-repo-name.git
cd your-repo-name
```
### 2. Configure the Database

Update the `application.properties` file in the `src/main/resources` directory with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/car_management
spring.datasource.username=your-username
spring.datasource.password=your-password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3. Access the Application

- The application will be available at: `http://localhost:8081`

---

---

## API Endpoints

### 1. Add a New Car

**URL:** `/api/cars`  
**Method:** `POST`  
**Request Body:**

```json
{
  "name": "string",
  "model": "string",
  "year": 2100,
  "price": 50000,
  "color": "string",
  "fuelType": "string"
}
```

**Response:** Returns the created car object with an auto-generated ID.

### 2. Get All Cars

**URL:** `/api/cars`  
**Method:** `GET`  
**Response:** Returns a list of all cars.

### 3. Get a Car by ID

**URL:** `/api/cars/{id}`  
**Method:** `GET`  
**Path Parameter:** `id` (Long)  
**Response:** Returns the car details for the given ID.

### 4. Update an Existing Car

**URL:** `/api/cars/{id}`  
**Method:** `PUT`  
**Path Parameter:** `id` (Long)  
**Request Body:**

```json
{
  "name": "string",
  "model": "string",
  "year": 2100,
  "price": 50000,
  "color": "string",
  "fuelType": "string"
}
```

**Response:** Returns the updated car object.

### 5. Delete a Car

**URL:** `/api/cars/{id}`  
**Method:** `DELETE`  
**Path Parameter:** `id` (Long)  
**Response:** Deletes the car with the given ID and returns a success message.

---

## Swagger Documentation

Swagger UI is available for interacting with the APIs and viewing their specifications.

- **URL:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---
