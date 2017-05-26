package com.example.olgacoll.airmns.model;

/**
 * Created by olgacoll on 26/5/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 *
 * @author ericayala
 */
public class Availability {

    @SerializedName("date")
    @Expose
    Date date;
    @SerializedName("id_user")
    @Expose
    int id_user;
    @SerializedName("start_time")
    @Expose
    int start_time;
    @SerializedName("end_time")
    @Expose
    int end_time;

    public Availability(){}

    public Availability(Date date, int id_user, int start_time, int end_time) {
        this.date = date;
        this.id_user = id_user;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    //Getters and setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Date=" + date + ", id_user=" + id_user + ", start_time=" + start_time + ", end_time=" + end_time + '}';
    }
}

