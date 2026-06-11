# Simple Network Monitoring System (SimpleNMS)

A real-time network monitoring system built using **Spring Boot, Kafka, and API Gateway**, designed with **microservices architecture, JWT security, and rate limiting**.

---

# Architecture Diagram

           ┌─────────────────────┐
           │    Swagger / Client │
           │    (Port 8080)      │
           └─────────┬───────────┘
                     │
                     ▼
           ┌─────────────────────┐
           │     API Gateway     │
           │     (Port 8082)     │
           │---------------------│
           │  JWT Validation     │
           │  CORS Handling      │
           │  Rate Limiting      │
           │  Routing (/api)     │
           └─────────┬───────────┘
                     │
                     ▼
           ┌─────────────────────┐
           │     SimpleNMS       │
           │     (Port 8080)     │
           │---------------------│
           │  REST APIs          │
           │  Business Logic     │
           │  H2 Database        │
           └─────────┬───────────┘
                     │
                     ▼
           ┌─────────────────────┐
           │       Kafka         │
           │---------------------│
           │  Event Streaming    │
           └─────────┬───────────┘
                     │
                     ▼
           ┌─────────────────────┐
           │  Router Simulator   │
           │---------------------│
           │  Producer           │
           └─────────────────────┘


---

# Tech Stack

-  Java 17+
-  Spring Boot
-  Spring Cloud Gateway
-  Apache Kafka
-  H2 Database
-  JWT Authentication
-  Swagger (OpenAPI)
-  Redis (optional - rate limiting)

---

# Authentication

All APIs (except login) require JWT token.


Authorization: Bearer <JWT_TOKEN>

---

# URLs

| Component | URL |
|----------|-----|
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
| API Gateway | http://localhost:8082/api |
| Service | http://localhost:8080 |

---

#  API ENDPOINTS

---

## 1. LOGIN

### Endpoint

POST /auth/login

### CURL

curl -X POST "http://localhost:8082/api/auth/login?username=admin&password=admin"

### Response
```json
{
  "message": "Login Success ",
  "data": "JWT_TOKEN"
}


2. GET SINGLE DEVICE STATUS
Endpoint
POST /routers/getDeviceStatus

Request
JSON{  "deviceId": "device-1"}Show more lines
CURL
curl -X POST http://localhost:8082/api/routers/getDeviceStatus \
-H "Authorization: Bearer YOUR_TOKEN" \
-H "Content-Type: application/json" \
-d "{\"deviceId\":\"device-1\"}"


3. GET MULTIPLE DEVICE STATUS
Endpoint
POST /routers/getDeviceStatusMultiple

Request
JSON{  "deviceIds": ["device-1", "device-2"],  "page": 0,  "size": 2}Show more lines
CURL
curl -X POST http://localhost:8082/api/routers/getDeviceStatusMultiple \
-H "Authorization: Bearer YOUR_TOKEN" \
-H "Content-Type: application/json" \
-d "{\"deviceIds\":[\"device-1\",\"device-2\"],\"page\":0,\"size\":2}"


Swagger Testing
Steps

Open:

http://localhost:8080/swagger-ui/index.html

Click Authorize

Enter:
Bearer YOUR_TOKEN

Execute APIs 
