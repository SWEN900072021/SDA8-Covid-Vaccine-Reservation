package org.unimelb.cis.swen90007sda8.Models;

import org.unimelb.cis.swen90007sda8.Mappers.recipientMapper;

import java.sql.Date;
import java.util.Dictionary;

public class recipientModel extends userModel{
    private String firstName;
    private String lastName;
    private Date date;
    private Boolean vaccinated;
    private Integer timeslotID;
    public String identity;

    public recipientModel(String email) {
        super.email = email;
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
    void load(){
        Dictionary<Object, Object> currentUser = recipientMapper.findUserByEmail(super.email);
        if (this.timeslotID == null){
            this.timeslotID = (Integer) currentUser.get("bookedtimeslot");
        }
        if (this.vaccinated == null){
            this.vaccinated = (Boolean) currentUser.get("vaccinated");
        }
        if (this.date == null){
            this.date = (Date) currentUser.get("dateofbirth");
        }
        if (this.firstName == null){
            this.firstName = (String) currentUser.get("firstname");
        }
        if (this.lastName == null){
            this.lastName = (String) currentUser.get("lastname");
        }
        if (this.identity == null){
            this.identity = (String) currentUser.get("user_identity");
        }
    }
}
