# java-db-service
![Java CI](https://github.com/haolabs/java-db-service/actions/workflows/java.yml/badge.svg)

Spring Boot CRUD + pagination + 5 JUnit tests (H2, PostgreSQL-ready).

## Quickstart
**Prereqs:** JDK 21, Maven 3.9+
```bash
mvn -q -DskipTests=false test
mvn spring-boot:run
```
## API
	•	GET /items?page=0&size=20
	•	POST /items — body: {"name":"Pen","price":1.99}
	•	GET /items/{id}
	•	PUT /items/{id}
	•	DELETE /items/{id}
	•	GET /categories
	•	POST /categories

## Tech
	•	Spring Boot 3
	•	JPA (Hibernate)
	•	H2 (dev/test; PostgreSQL-ready)
	•	JUnit 5, MockMvc
	•	Pagination
	•	Maven
