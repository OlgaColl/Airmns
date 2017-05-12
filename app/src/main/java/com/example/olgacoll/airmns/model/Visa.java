package com.example.olgacoll.airmns.model;

import java.util.Date;

/**
 * Created by ericayala on 12/5/17.
 */

public class Visa extends PaymentMethod{

    //--Attributes--

    String cardholder;
    String card_number;
    Date expiry_date;



    //--Constructors--

    public Visa() {}

    public Visa(int id, String cardholder, String card_number, Date expiry_date) {
        super(id);
        this.cardholder = cardholder;
        this.card_number = card_number;
        this.expiry_date = expiry_date;
    }



    //--Getters & Setters--


    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }



    //ToString


    @Override
    public String toString() {
        return "Visa{" +
                "card_number='" + card_number + '\'' +
                '}';
    }
}
