package com.example.olgacoll.airmns.model;


import java.util.List;

/**
 * Created by olgacoll on 29/4/17.
 */

public class Professional extends User{

    private List<Boolean> availability;

    public Professional(){

    }

    public Professional(String mail, String password, String name, String lastname, String phone,  List<Boolean> availability){
        super(mail, password, name, lastname, phone);
        this.availability = availability;
    }

    public List<Boolean> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Boolean> availability) {
        this.availability = availability;
    }
}
