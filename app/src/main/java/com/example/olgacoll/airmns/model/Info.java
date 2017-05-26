package com.example.olgacoll.airmns.model;

/**
 * Created by olgacoll on 26/5/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 *
 * @author alumne
 */

public class Info {

    //--Attributes--
    @SerializedName("date_time")
    @Expose
    Date date_time;
    @SerializedName("phone_number")
    @Expose
    String phone_number;
    @SerializedName("mail")
    @Expose
    String mail;
    @SerializedName("customer_service_manager")
    @Expose
    String customer_service_manager;
    @SerializedName("app_information")
    @Expose
    String app_information;
    @SerializedName("company_info")
    @Expose
    String company_info;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("price_hour")
    @Expose
    double price_hour;
    @SerializedName("iva")
    @Expose
    double iva;

    //--Constructor--
    public Info() {
    }

    public Info(Date date_time, String phone_number, String mail, String customer_service_manager, String app_information, String company_info, String address, double price_hour, double iva) {
        this.date_time = date_time;
        this.phone_number = phone_number;
        this.mail = mail;
        this.customer_service_manager = customer_service_manager;
        this.app_information = app_information;
        this.company_info = company_info;
        this.address = address;
        this.price_hour = price_hour;
        this.iva = iva;
    }

    //Getters & Setters

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCustomer_service_manager() {
        return customer_service_manager;
    }

    public void setCustomer_service_manager(String customer_service_manager) {
        this.customer_service_manager = customer_service_manager;
    }

    public String getApp_information() {
        return app_information;
    }

    public void setApp_information(String app_information) {
        this.app_information = app_information;
    }

    public String getCompany_info() {
        return company_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice_hour() {
        return price_hour;
    }

    public void setPrice_hour(double price_hour) {
        this.price_hour = price_hour;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @Override
    public String toString() {
        return "Info{" +
                "date_time=" + date_time +
                ", phone_number='" + phone_number + '\'' +
                ", mail='" + mail + '\'' +
                ", customer_service_manager='" + customer_service_manager + '\'' +
                ", app_information='" + app_information + '\'' +
                ", company_info='" + company_info + '\'' +
                ", address='" + address + '\'' +
                ", price_hour=" + price_hour +
                ", iva=" + iva +
                '}';
    }
}

