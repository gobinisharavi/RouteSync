import React, { useState, useEffect } from 'react';
import './BusTracker.css';

const BusTracker = () => {
    const [isTracking, setIsTracking] = useState(false);
    const [currentCoordinates, setCurrentCoordinates] = useState({ lat: 0, lng: 0 });
    const [busStatus, setBusStatus] = useState("Not Started");
    const [distance, setDistance] = useState(0);

    let watchId = null;

    const startTripTracking = () => {
        if (navigator.geolocation) {
            setIsTracking(true);
            
            watchId = navigator.geolocation.watchPosition(
                (position) => {
                    const { latitude, longitude } = position.coords;
                    setCurrentCoordinates({ lat: latitude, lng: longitude });
                    sendLocationToBackend(latitude, longitude);
                },
                (error) => console.error("Error fetching GPS: ", error),
                { enableHighAccuracy: true, maximumAge: 0 }
            );
        } else {
            alert("Geolocation is not supported by your browser.");
        }
    };

    const stopTripTracking = () => {
        if (watchId) {
            navigator.geolocation.clearWatch(watchId);
        }
        setIsTracking(false);
        setBusStatus("Trip Ended");
    };

    const sendLocationToBackend = async (lat, lng) => {
        try {
            const response = await fetch('http://localhost:8080/api/bus/update-location', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    busNo: "TN-28-100",
                    latitude: lat,
                    longitude: lng
                })
            });
            const data = await response.json();
            setBusStatus(data.currentStop);
            setDistance(data.distanceToNextStop);
        } catch (error) {
            console.error("API Error: ", error);
        }
    };

    return (
        <div className="tracker-card">
            <h2>RouteSync - Bus Coordinator Console</h2>
            <p className="bus-id">Bus No: <strong>TN-28-100</strong></p>
            
            <div className="status-box">
                <span className={`status-dot ${isTracking ? 'active' : 'inactive'}`}></span>
                <span>Status: <strong>{busStatus}</strong></span>
            </div>

            <p>Next Stop Distance: <strong>{distance} meters</strong></p>

            <div className="button-group">
                {!isTracking ? (
                    <button className="btn-start" onClick={startTripTracking}>Start Auto Trip</button>
                ) : (
                    <button className="btn-stop" onClick={stopTripTracking}>Stop Trip</button>
                )}
            </div>

            <div className="coordinates-display">
                <small>Live GPS Feed: {currentCoordinates.lat.toFixed(4)}, {currentCoordinates.lng.toFixed(4)}</small>
            </div>
        </div>
    );
};
export default BusTracker;

