package com.example.swen90008sda8;


import java.sql.Date;

public class userModel {
    private String email;
    private String firstName;
    private String lastName;
    private Date date;
    private String identity;
    private String postcode;
    private String typeofprovider;
    private Boolean vaccinated;
    private String hcpName;

    public Boolean getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTypeofprovider() {
        return typeofprovider;
    }

    public void setTypeofprovider(String typeofprovider) {
        this.typeofprovider = typeofprovider;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        String s ="";
        s = s + "Name "+this.firstName+" "+this.lastName+" Identity "+ this.identity+"\n";
        return s;
    }
}
