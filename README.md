# Interview Product Assignment

Mr. Jigar, thank you for taking the time to interview me and for giving me the
opportunity to submit this assignment after the interview. I spent a reasonable
amount of time to develop a simple product website API backend where users can
view multiple products.

As discussed during the interview, here are the key requirements:
- Each product includes `id`, `name`, and `price`.
- Three endpoints are required:
- `GET /api/products` (get all products)
- `GET /api/products/{id}` (get a single product by ID)
- `POST /api/products` (create a new product)
- Data is stored in memory (no database), and user data/payment API is not required.

I did not implement unit tests for this assignment. If I am missing any requirements
or if you have any questions, please let me know. I am happy to make any necessary
adjustments.

**Project Overview**
- Language: Java 17
- Framework: Spring Boot
- Storage: In-memory `ConcurrentHashMap` with `AtomicLong` IDs
- API style: REST with JSON

**API Endpoints**
- `GET /api/products` -> `200 OK`, returns all products (empty list if none)
- `GET /api/products/{id}` -> `200 OK` for success, `404 Not Found` if missing
- `POST /api/products` -> `201 Created` with `Location` header, `400 Bad Request` for validation errors

**Sample Requests and Responses**

Create apples:
```
POST /api/products
```
```json
{
  "name": "Apples",
  "price": 4
}
```

Response:
```
HTTP/1.1 201 Created
Location: /api/products/1
```
```json
{
  "id": 1,
  "name": "Apples",
  "price": 4
}
```

Create oranges:
```
POST /api/products
```
```json
{
  "name": "Oranges",
  "price": 3
}
```

Response:
```
HTTP/1.1 201 Created
Location: /api/products/2
```
```json
{
  "id": 2,
  "name": "Oranges",
  "price": 3
}
```

Create bananas:
```
POST /api/products
```
```json
{
  "name": "Bananas",
  "price": 2
}
```

Response:
```
HTTP/1.1 201 Created
Location: /api/products/3
```
```json
{
  "id": 3,
  "name": "Bananas",
  "price": 2
}
```

Get apples:
```
GET /api/products/1
```

Response:
```
HTTP/1.1 200 OK
```
```json
{
  "id": 1,
  "name": "Apples",
  "price": 4
}
```

Get all products:
```
GET /api/products
```

Response:
```
HTTP/1.1 200 OK
```
```json
[
  {
    "id": 1,
    "name": "Apples",
    "price": 4
  },
  {
    "id": 2,
    "name": "Oranges",
    "price": 3
  },
  {
    "id": 3,
    "name": "Bananas",
    "price": 2
  }
]
```

Validation error example:
```
POST /api/products
```
```json
{
  "name": "",
  "price": 0
}
```

Response:
```
HTTP/1.1 400 Bad Request
```
```json
{
  "timestamp": "2026-02-24T00:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/products",
  "errors": [
    "name: name is required",
    "price: price must be greater than 0"
  ]
}
```

**Run From Prebuilt JAR**
1. Download the prebuilt JAR.
`Interview_Product_Assignment-0.0.1-SNAPSHOT.jar`
2. Run the JAR:
```bash
java -jar Interview_Product_Assignment-0.0.1-SNAPSHOT.jar
```
3. The API will be available at `http://localhost:8080`.

**Design Patterns Used**
- Layered Architecture (Controller-Service separation) keeps HTTP concerns in controllers and business logic in services.
- Service Abstraction/Strategy-like Pattern (ProductService interface + ProductServiceImpl implementation) so that the controller depends on the ProductService interface instead of a concrete class..
- DTO Pattern (request/response separation) keeps API contracts explicit and decoupled from the domain model.
- Exception Handling pattern via centralized `@RestControllerAdvice` provides consistent JSON error responses.
- Dependency Injection (Spring IoC) injects `ProductService` into the controller for loose coupling and testability.
