package com.example.olgacoll.airmns.model;

import java.util.List;

/**
 * Created by olgacoll on 29/4/17.
 */

public class Client extends User{

    private List<String> address;

    public Client(){

    }

    public Client(String mail, String password, String name, String lastname, String phone, List<String> address){
        super(mail, password, name, lastname, phone);
        this.address = address;
    }


    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
