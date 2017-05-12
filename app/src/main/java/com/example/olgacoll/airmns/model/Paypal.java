package com.example.olgacoll.airmns.model;

import java.util.Date;

/**
 * Created by ericayala on 12/5/17.
 */

public class Paypal extends PaymentMethod {

    //--Attributes--

    String email;
    String password;



    //--Constructors--

    public Paypal() { }

    public Paypal(int id, String email, String password) {
        super(id);
        this.email = email;
        this.password = password;
    }



    //--Getters & Setters--

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


    //ToString


    @Override
    public String toString() {
        return "Paypal{" +
                "email='" + email + '\'' +
                '}';
    }
    
}
