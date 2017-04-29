package com.example.olgacoll.airmns.model;

/**
 * Created by olgacoll on 21/4/17.
 */

public class User {

    private int id;
    private String mail;
    private String password;
    private String type;
    private String name;
    private String lastname;
    private String phone;


    public User(){

    }

    public User(String mail, String password, String name, String lastname, String phone) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public User(String mail, String password, String type, String name, String lastname, String phone) {
        this.mail = mail;
        this.password = password;
        this.type = type;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
