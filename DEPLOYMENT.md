# 🚀 Deployment Guide - Travel Management System

## Deployment Options

### 1. JAR Deployment (Recommended)

#### Build the JAR

```bash
mvn clean package
```

#### Run the JAR

```bash
java -jar target/travel-management-system-1.0.0.jar
```

#### Run with Custom Port

```bash
java -jar -Dserver.port=9090 target/travel-management-system-1.0.0.jar
```

#### Run with External Configuration

```bash
java -jar target/travel-management-system-1.0.0.jar --spring.config.location=file:/path/to/application.properties
```

---

### 2. Tomcat Deployment

#### Step 1: Modify pom.xml

Change packaging from `jar` to `war`:

```xml
<packaging>war</packaging>
```

Add Tomcat dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

#### Step 2: Extend SpringBootServletInitializer

Update `TravelManagementApplication.java`:

```java
@SpringBootApplication
public class TravelManagementApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TravelManagementApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelManagementApplication.class, args);
    }
}
```

#### Step 3: Build WAR

```bash
mvn clean package
```

#### Step 4: Deploy to Tomcat

Copy `target/travel-management-system-1.0.0.war` to Tomcat's `webapps/` directory.

Access at: `http://localhost:8080/travel-management-system-1.0.0/api/`

---

### 3. Docker Deployment

#### Create Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/travel-management-system-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### Build Docker Image

```bash
docker build -t travel-management-system:1.0.0 .
```

#### Run Docker Container

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/travel_management \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  travel-management-system:1.0.0
```

#### Docker Compose (with MySQL)

Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: travel_management
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/travel_management
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - mysql

volumes:
  mysql-data:
```

Run with:

```bash
docker-compose up -d
```

---

### 4. Cloud Deployment

#### AWS Elastic Beanstalk

1. Install AWS CLI and EB CLI
2. Initialize EB:
   ```bash
   eb init -p java-17 travel-management-system
   ```
3. Create environment:
   ```bash
   eb create travel-env
   ```
4. Deploy:
   ```bash
   eb deploy
   ```

#### Heroku

1. Create `Procfile`:
   ```
   web: java -jar target/travel-management-system-1.0.0.jar
   ```

2. Create `system.properties`:
   ```
   java.runtime.version=17
   ```

3. Deploy:
   ```bash
   heroku create travel-management-app
   heroku addons:create cleardb:ignite
   git push heroku main
   ```

#### Google Cloud Platform (App Engine)

1. Create `app.yaml`:
   ```yaml
   runtime: java17
   instance_class: F2
   ```

2. Deploy:
   ```bash
   gcloud app deploy
   ```

---

### 5. Production Configuration

#### application-prod.properties

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Database Configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.root=WARN
logging.level.com.travel=INFO

# Security (Add Spring Security in production)
# spring.security.user.name=admin
# spring.security.user.password=${ADMIN_PASSWORD}
```

#### Run with Production Profile

```bash
java -jar target/travel-management-system-1.0.0.jar --spring.profiles.active=prod
```

---

### 6. Database Migration

For production, use Flyway or Liquibase for database migrations.

#### Add Flyway Dependency

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
</dependency>
```

#### Configure Flyway

```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.jpa.hibernate.ddl-auto=validate
```

---

### 7. Monitoring & Health Checks

#### Add Actuator

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### Configure Actuator

```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

Access health endpoint: `http://localhost:8080/api/actuator/health`

---

### 8. Performance Tuning

#### JVM Options

```bash
java -Xms512m -Xmx2048m -XX:+UseG1GC -jar target/travel-management-system-1.0.0.jar
```

#### Connection Pool Configuration

```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

---

### 9. SSL/HTTPS Configuration

#### Generate Keystore

```bash
keytool -genkeypair -alias travel-app -keyalg RSA -keysize 2048 \
  -storetype PKCS12 -keystore keystore.p12 -validity 3650
```

#### Configure SSL

```properties
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your-password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=travel-app
```

---

### 10. Backup & Recovery

#### Database Backup

```bash
mysqldump -u root -p travel_management > backup.sql
```

#### Database Restore

```bash
mysql -u root -p travel_management < backup.sql
```

---

## Environment Variables

Set these environment variables for production:

```bash
export DATABASE_URL=jdbc:mysql://localhost:3306/travel_management
export DATABASE_USERNAME=root
export DATABASE_PASSWORD=your-secure-password
export SERVER_PORT=8080
```

---

## Troubleshooting

### Port Already in Use

```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>
```

### Database Connection Issues

- Verify MySQL is running
- Check database credentials
- Ensure database exists
- Check firewall settings

### Memory Issues

Increase heap size:

```bash
java -Xmx4g -jar target/travel-management-system-1.0.0.jar
```

---

**For any deployment issues, check the logs:**

```bash
tail -f logs/spring.log
```
