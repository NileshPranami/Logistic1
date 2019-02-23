package com.creation.nilesh.logistic;

public class CustomerData {

    String userName,userEmail,contactNumber;

    public CustomerData(){}

    public CustomerData(String userName, String userEmail, String contactNumber) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.contactNumber = contactNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
