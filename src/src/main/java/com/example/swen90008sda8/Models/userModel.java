package com.example.swen90008sda8.Models;


import com.example.swen90008sda8.Mappers.UserMapper;

import java.sql.Date;
import java.util.Dictionary;

public class userModel {
    private String email;
    private String firstName;
    private String lastName;
    private Date date;
    private String identity;
    private String postcode;
    private String typeofprovider;
    private Boolean vaccinated;

    public String getHcpName() {
        if (this.hcpName == null){
            load();
        }
        return hcpName;
    }

    public void setHcpName(String hcpName) {
        this.hcpName = hcpName;
    }

    private String hcpName;

    public Integer getTimeslotID() {
        if (this.timeslotID == null){
            load();
        }
        return timeslotID;
    }

    public void setTimeslotID(Integer timeslotID) {
        this.timeslotID = timeslotID;
    }

    private Integer timeslotID;

    public Boolean getVaccinated() {
        if (this.vaccinated == null){
            load();
        }
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Date getDate() {
        if (this.date == null){
            load();
        }
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        if (this.firstName == null){
            load();
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        if (this.lastName == null){
            load();
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        if (this.identity == null){
            load();
        }
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPostcode() {
        if (this.postcode == null){
            load();
        }
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTypeofprovider() {
        if (this.typeofprovider == null){
            load();
        }
        return typeofprovider;
    }

    public void setTypeofprovider(String typeofprovider) {
        this.typeofprovider = typeofprovider;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    void load(){
        Dictionary currentUser = UserMapper.findUserByEmail(email);
        if (this.hcpName == null){
            this.hcpName = (String) currentUser.get("hcpname");
        }
        if (this.timeslotID == null){
            this.timeslotID = (Integer) currentUser.get("bookedtimeslot");
        }
        if (this.vaccinated == null){
            this.vaccinated = (Boolean) currentUser.get("vaccinated");;
        }
        if (this.date == null){
            this.date = (Date) currentUser.get("dateofbirth");;
        }
        if (this.firstName == null){
            this.firstName = (String) currentUser.get("firstname");;
        }
        if (this.lastName == null){
            this.lastName = (String) currentUser.get("lastname");;
        }
        if (this.identity == null){
            this.identity = (String) currentUser.get("user_identity");;
        }
        if (this.postcode == null){
            this.postcode = (String) currentUser.get("postcode");
        }
        if (this.typeofprovider == null){
            this.typeofprovider = (String) currentUser.get("typeofprovider");
        }
    }

    public String toString(){
        String s ="";
        s = s + "Name "+this.firstName+" "+this.lastName+" Identity "+ this.identity+"\n";
        return s;
    }
}
