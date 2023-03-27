package com.example.foodapp.Adapters.All_Modals;

public class Users {
    private String email;
    private String password,userID;

    public Users(String email, String password, String userID) {
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Users() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
