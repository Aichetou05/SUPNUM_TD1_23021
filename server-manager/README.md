# Server Manager - SOAP Web Service

## Overview

This project is a **SOAP Web Service** application built with **Spring Boot** for managing servers. It provides a SOAP (Simple Object Access Protocol) based API for performing CRUD operations and managing server states.

## What is SOAP?

**SOAP (Simple Object Access Protocol)** is an XML-based communication protocol used for exchanging information between applications over a network. Key features include:

- **XML-based**: All messages are built on XML
- **Platform Independent**: Works on any platform
- **Language Independent**: Can be used with any programming language
- **WSDL**: Uses WSDL (Web Services Description Language) to describe services
- **Contract-based**: Relies on a predefined contract (XSD Schema)

## Technologies Used

- **Spring Boot 3.5.8**: Main framework
- **Spring Web Services**: For building SOAP services
- **JAXB (Jakarta XML Binding)**: For converting Java objects to/from XML
- **Spring Data JPA**: For database access
- **MySQL**: Database
- **Maven**: Project management tool
- **XSD Schema**: For defining SOAP message structure

## Prerequisites

- **Java 17** or later
- **Maven 3.6+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

## Installation and Setup

### 1. Clone the Project

```bash
git clone <repository-url>
cd server-manager
```

### 2. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE soatd_soap;
```

### 3. Database Configuration

Edit the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/soatd_soap
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

Or using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

## SOAP Architecture in the Project

### 1. XSD Schema (`server.xsd`)

The `server.xsd` file contains definitions for all data types and messages used in the SOAP service:

- **Types**: Definition of `server` type (id, name, ipAddress, running, etc.)
- **Request Messages**: Request messages for each operation
- **Response Messages**: Response messages for each operation

### 2. WSDL (Web Services Description Language)

After running the application, you can access the WSDL file at:

```
http://localhost:8080/ws/servers.wsdl
```

The WSDL file describes:
- **Port Type**: Port type (ServersPort)
- **Operations**: Available operations
- **Messages**: Message structure
- **Bindings**: Binding operations to SOAP protocol

### 3. SOAP Endpoints

All SOAP endpoints are located in `ServerEndpoint.java` and use:

- `@Endpoint`: To specify that the class is a SOAP endpoint
- `@PayloadRoot`: To specify namespace and operation name
- `@RequestPayload`: To specify request parameters
- `@ResponsePayload`: To specify response type

## Available SOAP Operations

### 1. Create Server

**Request:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://supnum.mr/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:createServerRequest>
         <ser:name>Server-01</ser:name>
         <ser:ipAddress>192.168.1.100</ser:ipAddress>
      </ser:createServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

**Response:**
```xml
<soap:Envelope>
   <soap:Body>
      <ns2:createServerResponse>
         <ns2:server>
            <ns2:id>1</ns2:id>
            <ns2:name>Server-01</ns2:name>
            <ns2:ipAddress>192.168.1.100</ns2:ipAddress>
            <ns2:running>false</ns2:running>
         </ns2:server>
      </ns2:createServerResponse>
   </soap:Body>
</soap:Envelope>
```

### 2. Get All Servers

**Request:**
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <ser:getAllServersRequest/>
   </soapenv:Body>
</soapenv:Envelope>
```

### 3. Rename Server

**Request:**
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <ser:renameServerRequest>
         <ser:id>1</ser:id>
         <ser:newName>Server-Updated</ser:newName>
      </ser:renameServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 4. Get Server Status

**Request:**
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <ser:getServerStatusRequest>
         <ser:id>1</ser:id>
      </ser:getServerStatusRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 5. Start Server

**Request:**
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <ser:startServerRequest>
         <ser:id>1</ser:id>
      </ser:startServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 6. Stop Server

**Request:**
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <ser:stopServerRequest>
         <ser:id>1</ser:id>
      </ser:stopServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 7. Delete Server

**Request:**
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <ser:deleteServerRequest>
         <ser:id>1</ser:id>
      </ser:deleteServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## Testing SOAP Services

### Using SoapUI

1. Download and install [SoapUI](https://www.soapui.org/)
2. Create a new project
3. Enter the WSDL URL: `http://localhost:8080/ws/servers.wsdl`
4. SoapUI will automatically create all operations
5. You can test each operation directly

### Using Postman

1. Open Postman
2. Create a new POST request
3. URL: `http://localhost:8080/ws`
4. In Headers, add:
   - `Content-Type: text/xml`
   - `SOAPAction: http://supnum.mr/servers/createServerRequest`
5. In Body, select `raw` and `XML`, then enter the request XML

### Using cURL

```bash
curl --location --request POST 'http://localhost:8080/ws' \
--header 'Content-Type: text/xml' \
--header 'SOAPAction: http://supnum.mr/servers/createServerRequest' \
--data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.mr/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:createServerRequest>
         <ser:name>Test-Server</ser:name>
         <ser:ipAddress>192.168.1.50</ser:ipAddress>
      </ser:createServerRequest>
   </soapenv:Body>
</soapenv:Envelope>'
```

## Project Structure

```
server-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── mr/supnum/server_manager/
│   │   │       ├── config/
│   │   │       │   └── WebServiceConfig.java      # SOAP and WSDL configuration
│   │   │       ├── endpoint/
│   │   │       │   └── ServerEndpoint.java        # SOAP endpoints
│   │   │       ├── entities/
│   │   │       │   └── Server.java                # Database entity
│   │   │       ├── exception/
│   │   │       │   ├── ServerNotFoundException.java
│   │   │       │   └── ServerDeletionException.java
│   │   │       ├── repository/
│   │   │       │   └── ServerRepository.java      # JPA Repository
│   │   │       ├── service/
│   │   │       │   ├── ServerService.java         # Service interface
│   │   │       │   └── ServerServiceImpl.java     # Service implementation
│   │   │       └── ServerManagerApplication.java  # Application entry point
│   │   └── resources/
│   │       ├── application.properties             # Application settings
│   │       └── server.xsd                         # XML Schema definition
│   └── test/
└── pom.xml                                         # Maven configuration
```

## How SOAP Works in This Project

### 1. Generating Java Classes from XSD

The project uses `jaxb2-maven-plugin` to automatically generate Java classes from the `server.xsd` file:

- Generated classes are located in: `target/generated-sources/jaxb/mr/supnum/server_manager/wsdl/`
- These classes represent data types in SOAP messages

### 2. Configuring MessageDispatcherServlet

In `WebServiceConfig.java`:
- `MessageDispatcherServlet` is registered on the `/ws/*` path
- This servlet handles all SOAP requests

### 3. WSDL Configuration

- WSDL is automatically generated from XSD Schema
- Available at: `http://localhost:8080/ws/servers.wsdl`
- Namespace: `http://supnum.mr/servers`

### 4. Request Processing

1. SOAP request arrives at `/ws`
2. `MessageDispatcherServlet` identifies the required operation from `PayloadRoot`
3. XML is converted to Java object using JAXB
4. The appropriate method in `ServerEndpoint` is called
5. The result is converted to XML and sent as a SOAP response

## Namespace and Port

- **Namespace URI**: `http://supnum.mr/servers`
- **Port Type**: `ServersPort`
- **Location URI**: `/ws`
- **WSDL URL**: `http://localhost:8080/ws/servers.wsdl`

## Database

JPA/Hibernate is used with MySQL:

- **Table**: `servers`
- **Columns**: id, name, ip_address, running, created_at, updated_at
- Table is automatically created on startup (`ddl-auto=update`)

## Key Features

✅ **Complete SOAP Web Services** with WSDL  
✅ **Contract-First Approach**: Starting from XSD Schema  
✅ **JAXB** for XML ↔ Java Objects conversion  
✅ **Spring Data JPA** for database management  
✅ **Custom Exception Handling**  
✅ **Auto-generated WSDL** from XSD  

## Future Development

- Add WS-Security for authentication
- Add Logging for requests and responses
- Add Data Validation
- Add Unit Tests for Endpoints

## Contributing

Please open an Issue or Pull Request for any improvements or fixes.

## License

This project is for educational purposes as part of the SOA (Service-Oriented Architecture) course.

---

**Note**: This project is part of an exercise in the SOA course (TD1 - Exercice 2) and aims to demonstrate how to build SOAP services using Spring Boot.
