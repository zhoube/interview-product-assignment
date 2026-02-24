# Interview Product Assignment

Mr. Jigar, thank you for taking the time to interview me and for giving me the
opportunity to submit this assignment after the interview. As discussed during the interview,
I have developed a simple product website API Backend where users can view multiple products.
Each product includes `id`, `name`, and `price`. The API exposes three endpoints:
get all products, get a single product, and create a product. Data is stored in
memory (no database), and user data/payment API is not required.

**Project Overview**
- Language: Java 17
- Framework: Spring Boot
- Storage: In-memory `ConcurrentHashMap` with `AtomicLong` IDs
- API style: REST with JSON

**API Endpoints**
- `GET /api/products`
  - Response `200 OK`
  - Returns all products (empty list if none)
- `GET /api/products/{id}`
  - Response `200 OK`
  - Returns a single product
  - Response `404 Not Found` if the product does not exist
- `POST /api/products`
  - Request body: JSON with `name` and `price`
  - Response `201 Created` with `Location` header
  - Response `400 Bad Request` for validation errors

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
1. Download the prebuilt JAR (provided with the submission).
2. Run the JAR:
```bash
java -jar Interview_Product_Assignment-0.0.1-SNAPSHOT.jar
```
3. The API will be available at `http://localhost:8080`.

**Design Patterns Used**
- Layered Architecture (Controller-Service separation)
  - Controllers handle HTTP concerns, services handle business logic and storage.
- Strategy (ProductService interface + ProductServiceImpl implementation)
  - Allows swapping storage strategies without changing the controller.
- DTO Pattern (request/response separation)
  - Keeps API contracts explicit and decoupled from the domain model.
- Exception Handling pattern via centralized `@RestControllerAdvice`
  - Consistent JSON error format for all errors.
- Dependency Injection (Spring IoC)
  - Spring injects the `ProductService` into the controller, promoting testability and loose coupling.
