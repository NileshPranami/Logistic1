package com.creation.nilesh.logistic;

public class DriverData {
    String driverName,driverEmail,vehicleType;

    public DriverData(String driverName, String driverEmail, String vehicleType) {
        this.driverName = driverName;
        this.driverEmail = driverEmail;
        this.vehicleType = vehicleType;
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
}
