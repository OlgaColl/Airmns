package com.example.olgacoll.airmns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Reserve {

    @SerializedName("id_reserve")
    @Expose
    int id_reserve;
    @SerializedName("id_user_client")
    @Expose
    int id_user_client;
    @SerializedName("id_user_professional")
    @Expose
    int id_user_professional;
    @SerializedName("id_address")
    @Expose
    int id_address;
    @SerializedName("date_time")
    @Expose
    Date date_time;
    @SerializedName("start_time")
    @Expose
    int start_time;
    @SerializedName("long_time")
    @Expose
    int long_time;
    @SerializedName("total_price")
    @Expose
    double total_price;
    @SerializedName("observations")
    @Expose
    String observations;
    @SerializedName("qualification_service")
    @Expose
    int qualification_service;
    @SerializedName("comments")
    @Expose
    String comments;
    @SerializedName("iva")
    @Expose
    double iva;

    public Reserve(){}

    public Reserve(int id_reserve, int qualification_service, String comments) {
        this.id_reserve = id_reserve;
        this.qualification_service = qualification_service;
        this.comments = comments;
    }

    public Reserve(int id_user_client, int id_user_professional, int id_address, Date date_time, int start_time, int long_time, double total_price, String observations, Double iva) {
        this.id_user_client = id_user_client;
        this.id_user_professional = id_user_professional;
        this.id_address = id_address;
        this.date_time = date_time;
        this.start_time = start_time;
        this.long_time = long_time;
        this.total_price = total_price;
        this.observations = observations;
        this.iva=iva;
    }

    public Reserve(int id_reserve, int id_user_client, int id_user_professional, int id_address, Date date_time,  int start_time, int long_time, double total_price, String observations, int qualification_service, String comments, double iva) {
        this.id_reserve = id_reserve;
        this.id_user_client = id_user_client;
        this.id_user_professional = id_user_professional;
        this.id_address = id_address;
        this.date_time = date_time;
        this.start_time = start_time;
        this.long_time = long_time;
        this.total_price = total_price;
        this.observations = observations;
        this.qualification_service = qualification_service;
        this.comments = comments;
        this.iva = iva;
    }


    //Getters and Setters

    public int getId_reserve() {
        return id_reserve;
    }

    public void setId_reserve(int id_reserve) {
        this.id_reserve = id_reserve;
    }

    public int getId_user_client() {
        return id_user_client;
    }

    public void setId_user_client(int id_user_client) {
        this.id_user_client = id_user_client;
    }

    public int getId_user_professional() {
        return id_user_professional;
    }

    public void setId_user_professional(int id_user_professional) {
        this.id_user_professional = id_user_professional;
    }

    public int getId_address() {
        return id_address;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getLong_time() {
        return long_time;
    }

    public void setLong_time(int long_time) {
        this.long_time = long_time;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getQualification_service() {
        return qualification_service;
    }

    public void setQualification_service(int qualification_service) {
        this.qualification_service = qualification_service;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }
}