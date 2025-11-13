# PocketExpense — Personal Expense Tracker API

A beginner-friendly Spring Boot REST API to track personal expenses by category and month.

## Features
- Add / view / update / delete expenses
- Categorize expenses
- Monthly budget with threshold alert
- Summary endpoint (monthly totals and per-category breakdown)
- Uses H2 in-memory DB by default (easy to run). Switch to MySQL in application.properties.

## Run
Requirements: Java 17+, Maven

1. Build: `mvn clean package`
2. Run: `mvn spring-boot:run`
3. H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:pocketdb)

## Project structure
Core packages: controller, service, repository, entity, dto, exception, util

## Add to resume
- Built a Spring Boot REST API for personal expense tracking
- Implemented JPA entities, validation, summary and budget alert logic
- Dockerize / add authentication / deploy to Heroku (optional next steps)

