# ⚡ Quick Start Guide - Travel Management System

Get your Travel Management System up and running in 5 minutes!

## 🎯 Prerequisites Checklist

- [ ] Java 17 installed (`java -version`)
- [ ] Maven installed (`mvn -version`)
- [ ] MySQL installed and running
- [ ] Git installed

---

## 🚀 5-Minute Setup

### Step 1: Clone Repository (30 seconds)

```bash
git clone https://github.com/rahul700raj/travel-management-system.git
cd travel-management-system
```

### Step 2: Setup Database (1 minute)

Open MySQL and run:

```sql
CREATE DATABASE travel_management;
```

Or use command line:

```bash
mysql -u root -p -e "CREATE DATABASE travel_management;"
```

### Step 3: Configure Database (30 seconds)

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Step 4: Build Project (2 minutes)

```bash
mvn clean install
```

### Step 5: Run Application (30 seconds)

```bash
mvn spring-boot:run
```

**That's it! 🎉**

---

## 🌐 Access Your Application

### Main Application
```
http://localhost:8080/api
```

### Swagger UI (API Documentation)
```
http://localhost:8080/api/swagger-ui.html
```

### Health Check
```
http://localhost:8080/api/actuator/health
```

---

## 🧪 Quick Test

### Test 1: Create a User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "phone": "9876543210",
    "address": "Mumbai, India",
    "role": "CUSTOMER"
  }'
```

### Test 2: Get All Users

```bash
curl http://localhost:8080/api/users
```

### Test 3: Create a Package

```bash
curl -X POST http://localhost:8080/api/packages \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Goa Beach Paradise",
    "destination": "Goa",
    "description": "5 days beach vacation",
    "durationDays": 5,
    "price": 25000,
    "availableSeats": 20,
    "packageType": "DELUXE",
    "inclusions": "Hotel, Meals, Transport"
  }'
```

---

## 📊 Sample Data (Optional)

The application includes sample data in `src/main/resources/data.sql`.

To enable it, add to `application.properties`:

```properties
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

This will create:
- 4 sample users
- 5 travel packages
- 5 hotels
- 3 bookings

---

## 🎨 Using Swagger UI

1. Open browser: `http://localhost:8080/api/swagger-ui.html`
2. Expand any API section (Users, Packages, Bookings, Hotels)
3. Click "Try it out"
4. Fill in the request body
5. Click "Execute"
6. See the response!

---

## 🔧 Common Issues & Solutions

### Issue 1: Port 8080 Already in Use

**Solution:** Change port in `application.properties`:
```properties
server.port=9090
```

### Issue 2: Database Connection Failed

**Solution:** Verify MySQL is running:
```bash
# Windows
net start MySQL80

# Linux/Mac
sudo systemctl start mysql
```

### Issue 3: Maven Build Failed

**Solution:** Clean and rebuild:
```bash
mvn clean
mvn install -U
```

### Issue 4: Java Version Mismatch

**Solution:** Check Java version:
```bash
java -version
# Should be 17 or higher
```

---

## 📱 API Endpoints Quick Reference

### Users
- `POST /api/users` - Create user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Packages
- `POST /api/packages` - Create package
- `GET /api/packages` - Get all packages
- `GET /api/packages/active` - Get active packages
- `GET /api/packages/search?destination=Goa` - Search packages

### Bookings
- `POST /api/bookings` - Create booking
- `GET /api/bookings` - Get all bookings
- `GET /api/bookings/user/{userId}` - Get user bookings
- `PATCH /api/bookings/{id}/status?status=CONFIRMED` - Update status

### Hotels
- `POST /api/hotels` - Create hotel
- `GET /api/hotels` - Get all hotels
- `GET /api/hotels/search?location=Goa` - Search hotels

---

## 🎓 Next Steps

1. ✅ Explore Swagger UI for all APIs
2. ✅ Read [API_EXAMPLES.md](API_EXAMPLES.md) for detailed examples
3. ✅ Check [DEPLOYMENT.md](DEPLOYMENT.md) for production deployment
4. ✅ Customize entities and add new features
5. ✅ Add Spring Security for authentication

---

## 📚 Documentation

- [README.md](README.md) - Complete project documentation
- [API_EXAMPLES.md](API_EXAMPLES.md) - API request/response examples
- [DEPLOYMENT.md](DEPLOYMENT.md) - Deployment guide

---

## 🆘 Need Help?

- Check Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- View logs: Check console output
- Database issues: Verify MySQL connection
- Build issues: Run `mvn clean install -U`

---

## 🎉 Success Indicators

You'll know everything is working when:

✅ Application starts without errors  
✅ Swagger UI loads successfully  
✅ Database tables are created automatically  
✅ API calls return proper responses  
✅ Sample data loads (if enabled)

---

**Happy Coding! 🚀**

For detailed documentation, see [README.md](README.md)
