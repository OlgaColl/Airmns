package com.example.olgacoll.airmns.model;

/**
 * Created by ericayala on 12/5/17.
 */

public class PaymentMethod {

    //--Attributes--

    private int id;



    //--Constructors--

    public PaymentMethod() { }

    public PaymentMethod(int id) {
        this.id = id;
    }



    //--Getters & Setters--

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
