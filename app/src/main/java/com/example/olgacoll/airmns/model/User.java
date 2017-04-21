package com.example.olgacoll.airmns.model;

/**
 * Created by olgacoll on 21/4/17.
 */

public class User {
    private String user;
    private String password;
    private String type;

    public User(){

    }

    public User(String user, String password, String type){
        this.user = user;
        this.password = password;
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
