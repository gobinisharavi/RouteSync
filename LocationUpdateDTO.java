package com.routesync.dto;

public class LocationUpdateDTO {
    private String busNo;
    private double latitude;
    private double longitude;

    // Getters and Setters
    public String getBusNo() {
      return busNo;
    }
    public void setBusNo(String busNo) { 
      this.busNo = busNo;
    }
    public double getLatitude() { 
      return latitude;
    }
    public void setLatitude(double latitude) { 
      this.latitude = latitude; 
    }
    public double getLongitude() {
      return longitude;
    }
    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }
}
