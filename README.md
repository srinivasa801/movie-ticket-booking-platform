# 🎬 Movie Ticket Booking Platform

A **production-ready Spring Boot backend** for an online movie ticket booking platform supporting **B2C (customers)** and **B2B (theatre partners)** use cases.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.5.x**
- **Spring Data JPA (Hibernate)**
- **MySQL 8**
- **Springdoc OpenAPI (Swagger)**
- **Spring Boot Actuator**
- **JUnit 5 + Mockito**
- **Docker & Docker Compose**
- **Maven**

---

## 📌 Features

### 🎥 B2C (Customer)
- Browse movies by city & date
- View show timings
- Book tickets
- Discounts applied automatically

### 🏢 B2B (Theatre Partner)
- Create / update / delete shows
- Seat inventory management

### 💸 Business Rules
- **50% discount on 3rd ticket**
- **20% discount on afternoon shows (12 PM – 5 PM)**

---

## 🧱 Architecture

Controller → Service → Repository → Database
|
Pricing / Discount Engine

### Design Highlights
- Clean layered architecture
- DTO-based API responses (no entity exposure)
- Strategy pattern for discounts
- Global exception handling
- Transactional service layer
- Docker-ready & cloud-ready

---

## ⚙️ Prerequisites

- Java 17+
- Maven 3.9+
- Docker & Docker Compose (optional)
- MySQL (if not using Docker)

---

## ▶️ Running the Application (Local)

### 1️⃣ Build the application
```bash
mvn clean package -DskipTests
