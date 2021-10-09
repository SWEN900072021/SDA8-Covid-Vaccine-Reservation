package org.unimelb.cis.swen90007sda8.Models;

public class adminModel extends userModel{
    public String identity;

    public adminModel(String email) {
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

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }
}
