package org.unimelb.cis.swen90007sda8.Models;

import java.sql.Date;
import java.sql.Time;

public class timeRange {
    private String date;
    private String from;
    private String to;

    public timeRange(String date, String from, String to) {
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
