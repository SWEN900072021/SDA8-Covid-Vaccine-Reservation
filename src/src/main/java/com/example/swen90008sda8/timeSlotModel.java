package com.example.swen90008sda8;

import java.sql.Date;
import java.sql.Time;

public class timeSlotModel {
    private Date date;
    private Time from;
    private Time to;
    private String provider;
    private Integer numberofshots;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getNumberofshots() {
        return numberofshots;
    }

    public void setNumberofshots(Integer numberofshots) {
        this.numberofshots = numberofshots;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getFrom() {
        return from;
    }

    public void setFrom(Time from) {
        this.from = from;
    }

    public Time getTo() {
        return to;
    }

    public void setTo(Time to) {
        this.to = to;
    }

    public String toString(){
        String s ="";
        s = s + "Time "+this.date+" From "+ this.from+" To " + this.to +"\n";
        return s;
    }
}