# DiamondDiary

DiamondDiary is a Spring Boot backend application designed to manage softball player data, user accounts, and player-to-user relationships. It includes JWT-based authentication, geolocation services, and a REST API documented with Swagger/OpenAPI.

---

## Features

- User authentication using JWT
- Secure password storage with BCrypt
- User to Player relationship management (join table)
- Player management APIs
- Geocoding integration using OpenStreetMap (Nominatim)
- REST API documentation with Swagger/OpenAPI
- Spring Security configuration with stateless authentication

---
> [!IMPORTANT]
> ## Tech Stack
>
> - Java 17
> - Spring Boot
> - Spring Security
> - Spring Data JPA
> - PostgreSQL
> - JWT (JSON Web Tokens)
> - Swagger / OpenAPI (springdoc)
> - Maven

---

## Project Structure
```bash
locke.dustin.diamonddiary
│
├── auth # Security (JWT, filters, user details)
├── config # Security configuration
├── controller # REST controllers
├── service # Business logic
├── repository # Data access layer
├── entity # JPA entities
├── dto # Data transfer objects
├── component # Mappers and utility components
├── util # Helpers (geocoding, etc.)
```


---

## Authentication

This project uses JWT-based authentication.

- Users authenticate via `/auth/**`
- JWT is included in requests as:


```bash
Authorization: Bearer <token>
```



---

## Geocoding Service

Uses OpenStreetMap Nominatim API to convert addresses into coordinates.

### Configuration:

```bash 
geocoding.scheme=https
geocoding.host=nominatim.openstreetmap.org
```


## API Documentation

Once the application is running:
```bash
http://localhost:8080/swagger-ui/index.html
```


---

> [!IMPORTANT]
> ## Setup Instructions
>
> ### 1. Clone the repository
>
> ```bash
> git clone https://github.com/YOUR_USERNAME/DiamondDiary.git
> cd DiamondDiary
> ```
>
> ### 2. Set environment variables in IntelliJ
>
> ```bash
> DB_USERNAME=your_db_user
> DB_PASSWORD=your_db_password
> JWT_SECRET=your_secret_key
> ```
>
> ### 3. Run the application 
>
> Navigate to /DiamondDiary and run:
> ```bash
> mvn spring-boot:run
> ```

## Testing

Unit tests have been added across the service layer to verify business logic, dependency interactions, and expected failure scenarios.

### Current service layer test coverage includes:

#### GeoLocationService
- Finding locations by ID
- Updating location names
- Updating addresses and recalculating coordinates
- Handling missing locations with custom exceptions
#### MapLinkService
- Generating map links for supported mapping services
#### PlayerService
- Player creation, retrieval, updates, and exception handling
#### UserPlayerService
- User/player relationship management
- Validation of duplicate relationships and missing entities
#### UserService
- User creation and retrieval
- Validation and exception handling

Tests use Mockito to isolate service behavior by mocking repository, mapper, and external service dependencies.

## Future Improvements

- Refresh token implementation
- Role-based authorization (User / Coach / Admin)
- Improved player statistics tracking
- More unit + integration test coverage
- Flyway database migrations
- Docker support

## Author
Dustin Locke