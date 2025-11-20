# Server Manager

A RESTful API application for managing servers built with Spring Boot. This application allows you to create, retrieve, update, and manage server entities with their status (running/stopped).

## Features

- **Create Servers**: Add new servers with name and IP address
- **List Servers**: Retrieve all registered servers
- **Rename Servers**: Update server names
- **Server Status Management**: Start, stop, and check server status
- **Delete Servers**: Remove servers (only when stopped)
- **Automatic Timestamps**: Tracks creation and update times automatically

## Technology Stack

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - For database operations
- **MySQL** - Database
- **Lombok** - For reducing boilerplate code
- **Maven** - Build tool

## Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or compatible database)
- An IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Installation

1. **Clone the repository** (if applicable) or navigate to the project directory:
   ```bash
   cd server-manager
   ```

2. **Create MySQL Database**:
   ```sql
   CREATE DATABASE soa;
   ```

3. **Configure Database Connection**:
   
   Update the `src/main/resources/application.properties` file with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/soa
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Build the project**:
   ```bash
   mvn clean install
   ```

## Running the Application

### Using Maven:
```bash
mvn spring-boot:run
```

### Using the Maven Wrapper:
```bash
# On Windows
.\mvnw.cmd spring-boot:run

# On Linux/Mac
./mvnw spring-boot:run
```

### Using an IDE:
Run the `ServerManagerApplication.java` class directly from your IDE.

The application will start on `http://localhost:8080` by default.

## Documentation

La documentation OpenAPI JSON sera disponible sur :
- `http://localhost:8080/v3/api-docs`

L’interface Swagger UI sera accessible ici :
- `http://localhost:8080/swagger-ui/index.html`

## API Endpoints

### Base URL
```
http://localhost:8080/api/servers
```

### Available Endpoints

#### 1. Create a Server
- **POST** `/api/servers`
- **Request Body**:
  ```json
  {
    "name": "Web Server 1",
    "ipAddress": "192.168.1.100",
    "running": false
  }
  ```
- **Response**: `201 Created` with the created server object

#### 2. Get All Servers
- **GET** `/api/servers`
- **Response**: `200 OK` with a list of all servers

### Service Layer Methods (Not yet exposed via REST)

The following methods are available in the service layer but are not yet exposed as REST endpoints:

- `renameServer(Long id, String newName)` - Rename a server
- `getServerStatus(Long id)` - Get server status
- `startServer(Long id)` - Start a server
- `stopServer(Long id)` - Stop a server
- `deleteServer(Long id)` - Delete a server (only if stopped)

## Project Structure

```
server-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── mr/supnum/server_manager/
│   │   │       ├── contriller/          # REST Controllers
│   │   │       │   └── ServerController.java
│   │   │       ├── dto/                 # Data Transfer Objects
│   │   │       │   └── RenameServerRequest.java
│   │   │       ├── entity/              # JPA Entities
│   │   │       │   └── Server.java
│   │   │       ├── exception/           # Custom Exceptions
│   │   │       │   ├── ServerDeletionException.java
│   │   │       │   └── ServerNotFoundException.java
│   │   │       ├── repository/          # Data Access Layer
│   │   │       │   └── ServerRepository.java
│   │   │       ├── service/             # Business Logic
│   │   │       │   ├── ServerService.java
│   │   │       │   └── ServerServiceImpl.java
│   │   │       └── ServerManagerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Server Entity

The `Server` entity contains the following fields:

- `id` (Long): Auto-generated unique identifier
- `name` (String): Server name
- `ipAddress` (String): Server IP address
- `running` (boolean): Server status (true = running, false = stopped)
- `createdAt` (LocalDateTime): Automatic timestamp on creation
- `updatedAt` (LocalDateTime): Automatic timestamp on update

## Exception Handling

The application includes custom exceptions:

- **ServerNotFoundException**: Thrown when a server with the given ID doesn't exist
- **ServerDeletionException**: Thrown when attempting to delete a running server

## Database Configuration

The application uses JPA with Hibernate. The database schema is automatically updated based on entity changes (`spring.jpa.hibernate.ddl-auto=update`).

## Development

### Building the Project
```bash
mvn clean package
```

### Running Tests
```bash
mvn test
```

## License

This project is part of a course assignment (SOA TD1).

## Author

SUPNUM - L3 S5

