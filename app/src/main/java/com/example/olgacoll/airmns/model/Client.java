package com.example.olgacoll.airmns.model;

import java.util.List;

/**
 * Created by olgacoll on 29/4/17.
 */

public class Client extends User{

    private List<String> address;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getId() +
                ", mail='" + this.getMail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", type='" + this.getType() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", lastname='" + this.getLastname() + '\'' +
                ", prefix_phone='" + this.getPrefix_phone() + '\'' +
                ", phone='" + this.getPhone() + '\'' +
                '}';
    }
}
