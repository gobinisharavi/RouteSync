RouteSync: Campus College Bus Seat & ETA Tracker

RouteSync is an automated, lightweight campus bus tracking and geofencing system designed to eliminate manual driver/coordinator inputs. Utilizing the HTML5 Geolocation API on the client-side and a Java Spring Boot backend, the application calculates real-time distances using the Haversine Algorithm to trigger automatic bus-stop state transitions as the bus enters predefined geographic boundaries (geofences).

 Key Features

*   Automated Geofencing (No Manual Inputs): Drivers or coordinators simply click "Start Trip" once. The application continuously captures coordinates in the background and auto-updates status when entering a 100-meter boundary of any college bus stop.
*   Haversine Distance Calculator: Custom backend mathematical logic in Java to calculate the great-circle distance between two GPS coordinates (latitude/longitude) on the Earth's surface.
*   Live Status Dashboard: Responsive frontend dashboard showing dynamic route timeline progress (Active, En Route, Arrived) and current seat availability.
*   Visual Glow Indicators: Real-time visual tracking states styled dynamically using CSS3 transition effects and keyframe animations.

 Technology Stack

*   Frontend: React.js, JavaScript (ES6+), HTML5 Geolocation API, CSS3 (Keyframe Animations, Flexbox, CSS Grid)
*   Backend: Java 17+, Spring Boot (REST APIs, Spring Web), MVC Architecture
*   Database: MySQL (Relational DB Concepts, Transaction Status Mappings)
*   Version Control: Git & GitHub
