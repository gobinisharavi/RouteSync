package com.routesync.controller;

import com.routesync.dto.LocationUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bus")
@CrossOrigin(origins = "*") 
public class RouteSyncController {

    private static final double PAAVAI_STOP_LAT = 11.4746;
    private static final double PAAVAI_STOP_LON = 78.1633;
    private static final String PAAVAI_STOP_NAME = "Paavai College Main Campus";

    @PostMapping("/update-location")
    public ResponseEntity<Map<String, Object>> updateBusLocation(@RequestBody LocationUpdateDTO location) {
        
        double distanceInMeters = calculateHaversineDistance(
                location.getLatitude(), location.getLongitude(), 
                PAAVAI_STOP_LAT, PAAVAI_STOP_LON
        );

        Map<String, Object> response = new HashMap<>();
        response.put("busNo", location.getBusNo());
        response.put("distanceToNextStop", Math.round(distanceInMeters));

        if (distanceInMeters <= 100.0) {
            response.put("currentStop", PAAVAI_STOP_NAME);
            response.put("status", "Arrived");
        } else {
            response.put("currentStop", "En Route to " + PAAVAI_STOP_NAME);
            response.put("status", "En Route");
        }

        return ResponseEntity.ok(response);
    }

    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in km
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c * 1000; // Convert to meters
    }
}
