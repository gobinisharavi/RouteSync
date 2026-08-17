--1. Bus Stops details and GPS boundaries database
CREATE TABLE bus_stops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_no VARCHAR(10) NOT NULL,
    stop_name VARCHAR(100) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    stop_order INT NOT NULL
);

-- 2. Bus current dynamic live status 
CREATE TABLE bus_live_status (
    bus_no VARCHAR(10) PRIMARY KEY,
    current_stop VARCHAR(100) DEFAULT 'Not Started',
    seats_filled INT DEFAULT 0,
    total_seats INT DEFAULT 50,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
