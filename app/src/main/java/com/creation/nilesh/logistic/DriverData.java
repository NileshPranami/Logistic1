package com.creation.nilesh.logistic;

public class DriverData {
    String driverName;
    String driverEmail;
    String vehicleType;
    String contactNumber;


    public DriverData(){}

    public DriverData(String driverName, String driverEmail, String vehicleType, String contactNumber) {
        this.driverName = driverName;
        this.driverEmail = driverEmail;
        this.vehicleType = vehicleType;
        this.contactNumber = contactNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
