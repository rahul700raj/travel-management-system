# 📡 API Examples - Travel Management System

Complete API request and response examples for testing.

## Base URL
```
http://localhost:8080/api
```

---

## 👤 User APIs

### 1. Create User

**Request:**
```bash
POST /users
Content-Type: application/json

{
  "name": "Rahul Mishra",
  "email": "rahul@example.com",
  "phone": "9876543210",
  "address": "Mumbai, India",
  "role": "CUSTOMER"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Rahul Mishra",
  "email": "rahul@example.com",
  "phone": "9876543210",
  "address": "Mumbai, India",
  "role": "CUSTOMER",
  "active": true
}
```

### 2. Get All Users

**Request:**
```bash
GET /users
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Rahul Mishra",
    "email": "rahul@example.com",
    "phone": "9876543210",
    "address": "Mumbai, India",
    "role": "CUSTOMER",
    "active": true
  }
]
```

### 3. Get User by ID

**Request:**
```bash
GET /users/1
```

### 4. Update User

**Request:**
```bash
PUT /users/1
Content-Type: application/json

{
  "name": "Rahul Kumar Mishra",
  "email": "rahul@example.com",
  "phone": "9876543210",
  "address": "New Delhi, India"
}
```

### 5. Delete User

**Request:**
```bash
DELETE /users/1
```

---

## 📦 Travel Package APIs

### 1. Create Travel Package

**Request:**
```bash
POST /packages
Content-Type: application/json

{
  "name": "Goa Beach Paradise",
  "destination": "Goa",
  "description": "5 days beach vacation with water sports and sightseeing",
  "durationDays": 5,
  "price": 25000,
  "availableSeats": 20,
  "packageType": "DELUXE",
  "inclusions": "Hotel, Meals, Transport, Water Sports",
  "exclusions": "Personal expenses, Shopping",
  "imageUrl": "https://example.com/goa.jpg"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Goa Beach Paradise",
  "destination": "Goa",
  "description": "5 days beach vacation with water sports and sightseeing",
  "durationDays": 5,
  "price": 25000.00,
  "availableSeats": 20,
  "packageType": "DELUXE",
  "inclusions": "Hotel, Meals, Transport, Water Sports",
  "exclusions": "Personal expenses, Shopping",
  "active": true,
  "imageUrl": "https://example.com/goa.jpg"
}
```

### 2. Get All Packages

**Request:**
```bash
GET /packages
```

### 3. Get Active Packages

**Request:**
```bash
GET /packages/active
```

### 4. Get Available Packages

**Request:**
```bash
GET /packages/available
```

### 5. Search by Destination

**Request:**
```bash
GET /packages/search?destination=Goa
```

### 6. Filter by Price Range

**Request:**
```bash
GET /packages/price-range?minPrice=20000&maxPrice=30000
```

### 7. Update Package

**Request:**
```bash
PUT /packages/1
Content-Type: application/json

{
  "name": "Goa Beach Paradise - Updated",
  "destination": "Goa",
  "description": "Updated description",
  "durationDays": 5,
  "price": 27000,
  "availableSeats": 15,
  "packageType": "DELUXE",
  "inclusions": "Hotel, Meals, Transport, Water Sports, City Tour",
  "exclusions": "Personal expenses"
}
```

---

## 🎫 Booking APIs

### 1. Create Booking

**Request:**
```bash
POST /bookings
Content-Type: application/json

{
  "userId": 1,
  "packageId": 1,
  "bookingDate": "2024-01-15",
  "travelDate": "2024-02-15",
  "numberOfPeople": 2,
  "specialRequests": "Vegetarian meals preferred"
}
```

**Response:**
```json
{
  "id": 1,
  "userId": 1,
  "packageId": 1,
  "bookingDate": "2024-01-15",
  "travelDate": "2024-02-15",
  "numberOfPeople": 2,
  "totalAmount": 50000.00,
  "status": "PENDING",
  "paymentStatus": "PENDING",
  "specialRequests": "Vegetarian meals preferred",
  "userName": "Rahul Mishra",
  "packageName": "Goa Beach Paradise",
  "destination": "Goa"
}
```

### 2. Get All Bookings

**Request:**
```bash
GET /bookings
```

### 3. Get Bookings by User

**Request:**
```bash
GET /bookings/user/1
```

### 4. Get Bookings by Status

**Request:**
```bash
GET /bookings/status/CONFIRMED
```

**Status Options:** PENDING, CONFIRMED, CANCELLED, COMPLETED

### 5. Update Booking Status

**Request:**
```bash
PATCH /bookings/1/status?status=CONFIRMED
```

### 6. Update Payment Status

**Request:**
```bash
PATCH /bookings/1/payment?paymentStatus=PAID
```

**Payment Status Options:** PENDING, PAID, REFUNDED, FAILED

### 7. Delete Booking

**Request:**
```bash
DELETE /bookings/1
```

---

## 🏨 Hotel APIs

### 1. Create Hotel

**Request:**
```bash
POST /hotels
Content-Type: application/json

{
  "name": "Taj Goa Resort",
  "location": "Goa",
  "address": "Calangute Beach, Goa 403516",
  "starRating": 5,
  "pricePerNight": 8000,
  "amenities": "Pool, Spa, Restaurant, WiFi, Beach Access",
  "totalRooms": 100,
  "availableRooms": 85,
  "imageUrl": "https://example.com/taj-goa.jpg"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Taj Goa Resort",
  "location": "Goa",
  "address": "Calangute Beach, Goa 403516",
  "starRating": 5,
  "pricePerNight": 8000.00,
  "amenities": "Pool, Spa, Restaurant, WiFi, Beach Access",
  "totalRooms": 100,
  "availableRooms": 85,
  "active": true,
  "imageUrl": "https://example.com/taj-goa.jpg"
}
```

### 2. Get All Hotels

**Request:**
```bash
GET /hotels
```

### 3. Get Active Hotels

**Request:**
```bash
GET /hotels/active
```

### 4. Get Available Hotels

**Request:**
```bash
GET /hotels/available
```

### 5. Search by Location

**Request:**
```bash
GET /hotels/search?location=Goa
```

### 6. Update Hotel

**Request:**
```bash
PUT /hotels/1
Content-Type: application/json

{
  "name": "Taj Goa Resort & Spa",
  "location": "Goa",
  "address": "Calangute Beach, Goa 403516",
  "starRating": 5,
  "pricePerNight": 9000,
  "amenities": "Pool, Spa, Restaurant, WiFi, Beach Access, Gym",
  "totalRooms": 100,
  "availableRooms": 80
}
```

---

## 🧪 Testing with cURL

### Create User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "phone": "9999999999",
    "address": "Test Address",
    "role": "CUSTOMER"
  }'
```

### Get All Packages
```bash
curl -X GET http://localhost:8080/api/packages
```

### Create Booking
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "packageId": 1,
    "bookingDate": "2024-01-15",
    "travelDate": "2024-02-15",
    "numberOfPeople": 2,
    "specialRequests": "Window seat preferred"
  }'
```

### Update Booking Status
```bash
curl -X PATCH "http://localhost:8080/api/bookings/1/status?status=CONFIRMED"
```

---

## 🧪 Testing with Postman

1. Import the API collection
2. Set base URL: `http://localhost:8080/api`
3. Create requests for each endpoint
4. Test CRUD operations

---

## 📊 Response Status Codes

- `200 OK` - Successful GET/PUT request
- `201 Created` - Successful POST request
- `204 No Content` - Successful DELETE request
- `400 Bad Request` - Validation error
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

---

## 🔍 Error Response Format

```json
{
  "status": 400,
  "message": "User not found with id: 999",
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## 📝 Validation Errors

```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "phone": "Phone is required"
}
```

---

**Happy Testing! 🚀**
