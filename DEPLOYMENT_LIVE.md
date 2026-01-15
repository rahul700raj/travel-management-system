# 🌐 Live Deployment Guide - Travel Management System

Deploy your Travel Management System to production with these free hosting options.

---

## 🚀 Option 1: Render.com (Recommended)

### Why Render?
- ✅ Free tier available
- ✅ Automatic deployments from GitHub
- ✅ Easy database integration
- ✅ SSL certificates included
- ✅ No credit card required

### Step-by-Step Deployment

#### 1. Create Render Account
Visit: https://render.com and sign up with GitHub

#### 2. Create Web Service

1. Click **"New +"** → **"Web Service"**
2. Connect your GitHub repository: `rahul700raj/travel-management-system`
3. Configure:
   ```
   Name: travel-management-system
   Environment: Java
   Branch: main
   Build Command: mvn clean package -DskipTests
   Start Command: java -jar target/travel-management-system-1.0.0.jar
   ```

#### 3. Add Database

**Option A: Use Render PostgreSQL (Free)**
1. Click **"New +"** → **"PostgreSQL"**
2. Name: `travel-db`
3. Copy connection URL

**Option B: Use External MySQL**
- PlanetScale: https://planetscale.com
- Aiven: https://aiven.io

#### 4. Set Environment Variables

In Render dashboard, add:
```
SPRING_DATASOURCE_URL=jdbc:mysql://YOUR_DB_HOST:3306/travel_management
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
SERVER_PORT=8080
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

#### 5. Deploy!

Click **"Create Web Service"** - Deployment will start automatically.

#### 6. Access Your App

Your live URL: `https://travel-management-system.onrender.com`

Swagger UI: `https://travel-management-system.onrender.com/api/swagger-ui.html`

---

## 🚂 Option 2: Railway.app

### Step-by-Step

#### 1. Create Railway Account
Visit: https://railway.app

#### 2. New Project
1. Click **"New Project"**
2. Select **"Deploy from GitHub repo"**
3. Choose: `rahul700raj/travel-management-system`

#### 3. Add MySQL Database
1. Click **"+ New"**
2. Select **"Database"** → **"MySQL"**
3. Railway will provide connection details

#### 4. Configure Environment Variables
```
SPRING_DATASOURCE_URL=${{MySQL.DATABASE_URL}}
SPRING_DATASOURCE_USERNAME=${{MySQL.MYSQL_USER}}
SPRING_DATASOURCE_PASSWORD=${{MySQL.MYSQL_PASSWORD}}
SERVER_PORT=8080
```

#### 5. Deploy
Railway automatically deploys on every push to main branch.

**Live URL:** `https://travel-management-system-production.up.railway.app`

---

## 🟣 Option 3: Heroku

### Prerequisites
- Heroku account: https://heroku.com
- Heroku CLI installed

### Deployment Steps

#### 1. Login to Heroku
```bash
heroku login
```

#### 2. Create Heroku App
```bash
heroku create travel-management-system
```

#### 3. Add MySQL Database
```bash
# JawsDB MySQL (Free tier)
heroku addons:create jawsdb:kitefin

# Get database URL
heroku config:get JAWSDB_URL
```

#### 4. Set Environment Variables
```bash
heroku config:set SPRING_DATASOURCE_URL=jdbc:mysql://YOUR_DB_URL
heroku config:set SPRING_DATASOURCE_USERNAME=your_username
heroku config:set SPRING_DATASOURCE_PASSWORD=your_password
```

#### 5. Deploy
```bash
git push heroku main
```

#### 6. Open App
```bash
heroku open
```

**Live URL:** `https://travel-management-system.herokuapp.com`

---

## 🗄️ Free Database Options

### 1. PlanetScale (Recommended for MySQL)

**Features:**
- Free 5GB storage
- Automatic backups
- Branching support
- No credit card required

**Setup:**
1. Visit: https://planetscale.com
2. Create account
3. Create database: `travel_management`
4. Get connection string
5. Use in your app

**Connection String:**
```
jdbc:mysql://aws.connect.psdb.cloud/travel_management?sslMode=VERIFY_IDENTITY
```

### 2. Aiven MySQL

**Features:**
- Free tier available
- Multiple cloud providers
- Easy setup

**Setup:**
1. Visit: https://aiven.io
2. Create free MySQL service
3. Get connection details

### 3. Railway MySQL

**Features:**
- Integrated with Railway
- Easy setup
- Free tier

---

## 🔧 Production Configuration

### Update application.properties for Production

Create `application-prod.properties`:

```properties
# Server Configuration
server.port=${PORT:8080}

# Database Configuration
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Logging
logging.level.root=INFO
logging.level.com.travel=INFO

# Disable sample data in production
spring.jpa.defer-datasource-initialization=false
spring.sql.init.mode=never
```

### Run with Production Profile

```bash
java -jar -Dspring.profiles.active=prod target/travel-management-system-1.0.0.jar
```

---

## 📊 Monitoring Your Live App

### Health Check Endpoint
```
GET https://your-app-url.com/api/actuator/health
```

### Swagger Documentation
```
https://your-app-url.com/api/swagger-ui.html
```

### Test API
```bash
curl https://your-app-url.com/api/users
```

---

## 🔒 Security Considerations

### 1. Add Spring Security

Add to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### 2. Enable HTTPS
All platforms (Render, Railway, Heroku) provide free SSL certificates.

### 3. Environment Variables
Never commit sensitive data. Always use environment variables.

### 4. CORS Configuration
Add CORS configuration for frontend integration:

```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("https://your-frontend-url.com")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
```

---

## 🚨 Troubleshooting

### Issue 1: Build Fails
**Solution:** Check Java version in `system.properties`
```
java.runtime.version=17
```

### Issue 2: Database Connection Failed
**Solution:** Verify environment variables are set correctly

### Issue 3: Port Binding Error
**Solution:** Use `${PORT:8080}` in application.properties
```properties
server.port=${PORT:8080}
```

### Issue 4: Out of Memory
**Solution:** Increase heap size
```bash
java -Xmx512m -jar target/travel-management-system-1.0.0.jar
```

---

## 📈 Scaling Your App

### Render
- Upgrade to paid plan for more resources
- Enable auto-scaling

### Railway
- Increase memory/CPU in settings
- Add multiple replicas

### Heroku
- Scale dynos: `heroku ps:scale web=2`

---

## 🎯 Quick Deploy Checklist

- [ ] Choose hosting platform (Render/Railway/Heroku)
- [ ] Create account and connect GitHub
- [ ] Setup database (MySQL/PostgreSQL)
- [ ] Configure environment variables
- [ ] Deploy application
- [ ] Test health endpoint
- [ ] Access Swagger UI
- [ ] Test APIs
- [ ] Monitor logs
- [ ] Setup custom domain (optional)

---

## 🌟 Post-Deployment

### 1. Test Your APIs
```bash
# Health check
curl https://your-app-url.com/api/actuator/health

# Get users
curl https://your-app-url.com/api/users

# Create user
curl -X POST https://your-app-url.com/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Test","email":"test@example.com","phone":"9999999999","role":"CUSTOMER"}'
```

### 2. Share Your Live App
- Swagger UI: `https://your-app-url.com/api/swagger-ui.html`
- API Base URL: `https://your-app-url.com/api`

### 3. Monitor Performance
- Check application logs
- Monitor response times
- Track error rates

---

## 📞 Support

If you face any deployment issues:
1. Check platform documentation
2. Review application logs
3. Verify environment variables
4. Test database connection

---

**Your Travel Management System is ready to go live! 🚀**

Choose your preferred platform and deploy in minutes!
