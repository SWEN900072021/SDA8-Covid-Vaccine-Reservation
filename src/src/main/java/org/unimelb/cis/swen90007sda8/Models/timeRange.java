package org.unimelb.cis.swen90007sda8.Models;

import java.sql.Date;
import java.sql.Time;

public class timeRange {
    private Date date;
    private Time from;
    private Time to;

    public timeRange(Date date, Time from, Time to) {
        this.date = date;
        this.from = from;
        this.to = to;
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
}
