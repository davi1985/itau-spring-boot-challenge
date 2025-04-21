# Programming Challenge (Itaú)

> ⚠️ This project is part of my self-taught studies on Spring Boot and backend development with Java.  
> 📄 The challenge requirements are available in the file [`challenge.md`](./challenge.md) and can be read for full context.

This is a solution for the programming challenge proposed by Itaú Unibanco. The main goal is to build a REST API to handle transactions and return statistics calculated from the last 60 seconds. All data is stored in memory, without using any databases or external caches.

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Maven
- Java Records (Java 16+)
- JUnit 5
- Mockito
- Swagger/OpenAPI
- SLF4J + Logback (logging)

## ✅ Implemented Features

- [x] Receive transactions via `POST /transacao`
- [x] Return statistics for the last 60 seconds via `GET /estatistica`
- [x] Delete all transactions via `DELETE /transacao`
- [x] In-memory data storage (no databases or caches)
- [x] Proper status code handling (`201`, `422`, `400`, `200`)
- [x] Use of `record` classes for clean and immutable data modeling
- [x] Full validation for incoming transactions:
  - Value must be zero or greater
  - Timestamp must not be in the future
  - JSON must be valid

## 🚀 Upcoming Improvements

- [ ] Unit tests with JUnit and Mockito
- [ ] Integration tests using MockMvc
- [x] Logging to help with debugging and monitoring
- [ ] API documentation using Swagger/OpenAPI
- [ ] Global error handling with meaningful messages using `@ControllerAdvice`

