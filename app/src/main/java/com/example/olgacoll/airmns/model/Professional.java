package com.example.olgacoll.airmns.model;


import java.util.List;

/**
 * Created by olgacoll on 29/4/17.
 */

public class Professional extends User{

    //--Attributes--
    private List<Boolean> availability;

    //--Constructors--
    public Professional(){ }

    public Professional(String mail, String password, String name, String lastname, String prefix_phone, String phone){
        super(mail, password, name, lastname, prefix_phone, phone);
    }

    public Professional(String mail, String password, String name, String lastname, String prefix_phone, String phone,  List<Boolean> availability){
        super(mail, password, name, lastname, prefix_phone, phone);
        this.availability = availability;
    }



    //--Getters & Setters--

    public List<Boolean> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Boolean> availability) {
        this.availability = availability;
    }

}
