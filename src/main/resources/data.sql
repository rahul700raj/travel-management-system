-- Sample Users
INSERT INTO users (name, email, phone, password, address, role, active, created_at, updated_at) VALUES
('Admin User', 'admin@travel.com', '9876543210', 'admin123', 'Mumbai, India', 'ADMIN', true, NOW(), NOW()),
('Rahul Mishra', 'rahul@example.com', '9876543211', 'password123', 'Delhi, India', 'CUSTOMER', true, NOW(), NOW()),
('Priya Sharma', 'priya@example.com', '9876543212', 'password123', 'Bangalore, India', 'CUSTOMER', true, NOW(), NOW()),
('Travel Agent', 'agent@travel.com', '9876543213', 'agent123', 'Goa, India', 'AGENT', true, NOW(), NOW());

-- Sample Travel Packages
INSERT INTO travel_packages (name, destination, description, duration_days, price, available_seats, package_type, inclusions, exclusions, active, image_url, created_at, updated_at) VALUES
('Goa Beach Paradise', 'Goa', 'Enjoy 5 days of sun, sand, and sea with water sports and beach activities', 5, 25000.00, 20, 'DELUXE', 'Hotel, Meals, Transport, Water Sports', 'Personal expenses, Shopping', true, 'https://example.com/goa.jpg', NOW(), NOW()),
('Himalayan Adventure', 'Manali', 'Experience the thrill of mountains with trekking and camping', 7, 35000.00, 15, 'PREMIUM', 'Hotel, Meals, Transport, Trekking Guide', 'Personal expenses, Equipment rental', true, 'https://example.com/manali.jpg', NOW(), NOW()),
('Kerala Backwaters', 'Kerala', 'Relax in the serene backwaters with houseboat stay', 4, 20000.00, 25, 'STANDARD', 'Houseboat, Meals, Transport', 'Personal expenses, Shopping', true, 'https://example.com/kerala.jpg', NOW(), NOW()),
('Rajasthan Heritage Tour', 'Rajasthan', 'Explore the royal heritage of Rajasthan with palace visits', 6, 30000.00, 18, 'DELUXE', 'Hotel, Meals, Transport, Guide', 'Personal expenses, Entry fees', true, 'https://example.com/rajasthan.jpg', NOW(), NOW()),
('Ladakh Expedition', 'Ladakh', 'Adventure trip to the land of high passes', 10, 50000.00, 12, 'PREMIUM', 'Hotel, Meals, Transport, Oxygen support', 'Personal expenses, Medical insurance', true, 'https://example.com/ladakh.jpg', NOW(), NOW());

-- Sample Hotels
INSERT INTO hotels (name, location, address, star_rating, price_per_night, amenities, total_rooms, available_rooms, active, image_url, created_at, updated_at) VALUES
('Taj Goa Resort', 'Goa', 'Calangute Beach, Goa 403516', 5, 8000.00, 'Pool, Spa, Restaurant, WiFi, Beach Access', 100, 85, true, 'https://example.com/taj-goa.jpg', NOW(), NOW()),
('Manali Heights', 'Manali', 'Mall Road, Manali 175131', 4, 5000.00, 'Restaurant, WiFi, Mountain View, Parking', 50, 40, true, 'https://example.com/manali-heights.jpg', NOW(), NOW()),
('Kerala Houseboat', 'Kerala', 'Alleppey Backwaters, Kerala 688001', 3, 6000.00, 'AC, Meals, Scenic View, Private Deck', 20, 15, true, 'https://example.com/kerala-boat.jpg', NOW(), NOW()),
('Rajasthan Palace Hotel', 'Jaipur', 'Pink City, Jaipur 302001', 5, 10000.00, 'Pool, Spa, Restaurant, WiFi, Heritage Architecture', 80, 65, true, 'https://example.com/rajasthan-palace.jpg', NOW(), NOW()),
('Ladakh Mountain Lodge', 'Leh', 'Leh Market, Ladakh 194101', 3, 4000.00, 'Restaurant, WiFi, Oxygen Support, Parking', 30, 25, true, 'https://example.com/ladakh-lodge.jpg', NOW(), NOW());

-- Sample Bookings
INSERT INTO bookings (user_id, package_id, booking_date, travel_date, number_of_people, total_amount, status, payment_status, special_requests, created_at, updated_at) VALUES
(2, 1, '2024-01-10', '2024-02-15', 2, 50000.00, 'CONFIRMED', 'PAID', 'Vegetarian meals preferred', NOW(), NOW()),
(3, 3, '2024-01-12', '2024-03-01', 3, 60000.00, 'PENDING', 'PENDING', 'Need wheelchair accessible room', NOW(), NOW()),
(2, 2, '2024-01-15', '2024-04-10', 1, 35000.00, 'CONFIRMED', 'PAID', 'First time trekker', NOW(), NOW());
