package com.example.olgacoll.airmns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by olgacoll on 21/4/17.
 */

public class User {

    //--Attributes--
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("mail")
    @Expose
    String mail;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("lastname")
    @Expose
    String lastname;
    @SerializedName("prefix_phone")
    @Expose
    String prefix_phone;
    @SerializedName("phone")
    @Expose
    String phone;

    public User(){}

    public User(int id, String mail){
        this.id = id;
        this.mail = mail;
    }

    public User(int id, String mail, String password, String type, String name, String lastname, String prefix_phone, String phone){
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.type = type;
        this.name = name;
        this.lastname = lastname;
        this.prefix_phone = prefix_phone;
        this.phone = phone;
    }

    //--Getters & Setters--

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

    public String getPrefix_phone() {
        return prefix_phone;
    }

    public void setPrefix_phone(String prefix_phone) {
        this.prefix_phone = prefix_phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", prefix_phone='" + prefix_phone + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
