package org.unimelb.cis.swen90007sda8.Models;

public class timeSlotModel {


    private Integer id;
    private timeRange timeRange;
    private String provider;
    private Integer numberofshots;
    private vaccineModel vaccine;

    public vaccineModel getVaccine() {
        return vaccine;
    }

    public void setVaccine(vaccineModel vaccine) {
        this.vaccine = vaccine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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

    public timeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(timeRange timeRange) {
        this.timeRange = timeRange;
    }



}
