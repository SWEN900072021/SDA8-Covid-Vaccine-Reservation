package org.unimelb.cis.swen90007sda8.Models;


import org.unimelb.cis.swen90007sda8.Mappers.adminMapper;

import java.util.Dictionary;

public abstract class userModel {
    public String email;
    public String identity;



    public String getIdentity() {
        if (this.identity == null){
            load();
        }
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    abstract public String getFirstName();
    abstract public String getLastName();
    public Boolean getVaccinated(){
        return false;
    };
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    void load(){
        Dictionary<Object, Object> currentUser = adminMapper.findUserByEmail(email);
        if (this.identity == null){
            this.identity = (String) currentUser.get("identity");
        }
    }

}
