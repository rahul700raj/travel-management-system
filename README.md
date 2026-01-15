# 🌍 Travel Management System

A complete Travel Management System built with **Spring Boot**, **MySQL**, and **RESTful APIs**. This application provides comprehensive features for managing travel packages, bookings, hotels, and users.

## 🚀 Features

- **User Management**: Create, update, and manage users with role-based access (Admin, Customer, Agent)
- **Travel Package Management**: Browse, search, and manage travel packages with destinations, pricing, and availability
- **Booking System**: Complete booking workflow with status tracking and payment management
- **Hotel Management**: Manage hotel listings with location-based search and availability tracking
- **RESTful APIs**: Well-documented REST APIs for all operations
- **Swagger UI**: Interactive API documentation and testing interface
- **MySQL Database**: Robust data persistence with JPA/Hibernate
- **Validation**: Input validation using Bean Validation
- **Exception Handling**: Global exception handling for better error management

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **MySQL 8.0+**
- **Maven**
- **Lombok**
- **ModelMapper**
- **Springdoc OpenAPI (Swagger)**
- **Jakarta Validation**

## 📋 Prerequisites

Before running this application, make sure you have:

- Java 17 or higher installed
- Maven 3.6+ installed
- MySQL 8.0+ installed and running
- Git installed

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/rahul700raj/travel-management-system.git
cd travel-management-system
```

### 2. Configure MySQL Database

Create a MySQL database:

```sql
CREATE DATABASE travel_management;
```

Update database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/travel_management?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/travel-management-system-1.0.0.jar
```

The application will start on **http://localhost:8080/api**

## 📚 API Documentation

Once the application is running, access the Swagger UI at:

**http://localhost:8080/api/swagger-ui.html**

### API Endpoints Overview

#### User APIs (`/api/users`)
- `POST /users` - Create a new user
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `GET /users/email/{email}` - Get user by email
- `GET /users/active` - Get all active users
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user (soft delete)

#### Travel Package APIs (`/api/packages`)
- `POST /packages` - Create a new package
- `GET /packages` - Get all packages
- `GET /packages/{id}` - Get package by ID
- `GET /packages/active` - Get active packages
- `GET /packages/available` - Get available packages
- `GET /packages/search?destination={name}` - Search by destination
- `GET /packages/price-range?minPrice={min}&maxPrice={max}` - Filter by price
- `PUT /packages/{id}` - Update package
- `DELETE /packages/{id}` - Delete package

#### Booking APIs (`/api/bookings`)
- `POST /bookings` - Create a new booking
- `GET /bookings` - Get all bookings
- `GET /bookings/{id}` - Get booking by ID
- `GET /bookings/user/{userId}` - Get bookings by user
- `GET /bookings/status/{status}` - Get bookings by status
- `PATCH /bookings/{id}/status?status={status}` - Update booking status
- `PATCH /bookings/{id}/payment?paymentStatus={status}` - Update payment status
- `DELETE /bookings/{id}` - Delete booking

#### Hotel APIs (`/api/hotels`)
- `POST /hotels` - Create a new hotel
- `GET /hotels` - Get all hotels
- `GET /hotels/{id}` - Get hotel by ID
- `GET /hotels/active` - Get active hotels
- `GET /hotels/available` - Get available hotels
- `GET /hotels/search?location={name}` - Search by location
- `PUT /hotels/{id}` - Update hotel
- `DELETE /hotels/{id}` - Delete hotel

## 📝 Sample API Requests

### Create a User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Rahul Mishra",
    "email": "rahul@example.com",
    "phone": "9876543210",
    "address": "Mumbai, India",
    "role": "CUSTOMER"
  }'
```

### Create a Travel Package

```bash
curl -X POST http://localhost:8080/api/packages \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Goa Beach Paradise",
    "destination": "Goa",
    "description": "5 days beach vacation with water sports",
    "durationDays": 5,
    "price": 25000,
    "availableSeats": 20,
    "packageType": "DELUXE",
    "inclusions": "Hotel, Meals, Transport",
    "exclusions": "Personal expenses"
  }'
```

### Create a Booking

```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "packageId": 1,
    "bookingDate": "2024-01-15",
    "travelDate": "2024-02-01",
    "numberOfPeople": 2,
    "specialRequests": "Vegetarian meals preferred"
  }'
```

## 🗂️ Project Structure

```
travel-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/travel/
│   │   │   ├── controller/       # REST Controllers
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── entity/           # JPA Entities
│   │   │   ├── repository/       # JPA Repositories
│   │   │   ├── service/          # Business Logic
│   │   │   ├── exception/        # Exception Handlers
│   │   │   └── TravelManagementApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🔧 Configuration

Key configuration properties in `application.properties`:

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/travel_management
spring.jpa.hibernate.ddl-auto=update

# Swagger Configuration
springdoc.swagger-ui.path=/swagger-ui.html
```

## 🚢 Deployment

### Build JAR File

```bash
mvn clean package
```

The JAR file will be created in the `target/` directory.

### Run JAR File

```bash
java -jar target/travel-management-system-1.0.0.jar
```

### Deploy to Tomcat (Optional)

Change packaging to `war` in `pom.xml` and deploy the WAR file to Tomcat.

## 🧪 Testing

Run tests using Maven:

```bash
mvn test
```

## 📊 Database Schema

The application automatically creates the following tables:

- `users` - User information
- `travel_packages` - Travel package details
- `bookings` - Booking records
- `hotels` - Hotel information

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

**Rahul Mishra**
- GitHub: [@rahul700raj](https://github.com/rahul700raj)
- Email: rm2778643@gmail.com

## 🙏 Acknowledgments

- Spring Boot Team for the amazing framework
- MySQL for the robust database
- Swagger for API documentation

---

**Happy Coding! 🚀**
