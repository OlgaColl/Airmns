package com.example.olgacoll.airmns.model;

import java.util.List;

/**
 * Created by olgacoll on 29/4/17.
 */

public class Client extends User{

    //--Attributes--

    private List<String> address;

    //--Constructors--

    public Client(){ }

    public Client(int id, String mail, String password, String type, String name, String lastname, String prefix_phone, String phone){
        super(id, mail, password, type, name, lastname, prefix_phone, phone);
    }

    public Client(int id, String mail, String password, String type, String name, String lastname, String prefix_phone, String phone, List<String> address){
        super(id, mail, password, type, name, lastname, prefix_phone, phone);
        this.address = address;
    }



    //--Getters & Setters--

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
