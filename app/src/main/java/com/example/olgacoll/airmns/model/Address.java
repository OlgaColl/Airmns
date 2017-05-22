package com.example.olgacoll.airmns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by olgacoll on 9/5/17.
 */

public class Address {

    //--Attributes--
    @SerializedName("id_address")
    @Expose
    private int id_address;
    @SerializedName("id")
    @Expose
    private int id_user;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("floor")
    @Expose
    private String floor;
    @SerializedName("stair")
    @Expose
    private String stair;
    @SerializedName("door")
    @Expose
    private String door;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("postal_code")
    @Expose
    private String postal_code;

    //--Constructors--

    public Address(){

    }

    public Address(int id_address, int id_user, String street, String number, String floor, String stair, String door, String city, String postal_code) {
        this.id_address = id_address;
        this.id_user = id_user;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.stair = stair;
        this.door = door;
        this.city = city;
        this.postal_code = postal_code;
    }



    //--Getters & Setters--

    public int getId_address() {
        return id_address;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getStair() {
        return stair;
    }

    public void setStair(String stair) {
        this.stair = stair;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id_address=" + id_address +
                ", id_user=" + id_user +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", floor='" + floor + '\'' +
                ", stair='" + stair + '\'' +
                ", door='" + door + '\'' +
                ", city='" + city + '\'' +
                ", postal_code='" + postal_code + '\'' +
                '}';
    }
}
