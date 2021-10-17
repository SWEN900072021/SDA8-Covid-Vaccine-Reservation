package org.unimelb.cis.swen90007sda8.Models;

import org.unimelb.cis.swen90007sda8.Mappers.hcpMapper;

import java.util.Dictionary;

public class hcpModel extends userModel{
    public hcpModel(String email) {
        super.email = email;
    }

    private String postcode;
    private String typeofprovider;
    private String hcpName;
    public String identity;

    public String getEmail() {
        return super.email;
    }
    public void setEmail(String email) {
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
    public String getHcpName() {
        if (this.hcpName == null){
            load();
        }
        return hcpName;
    }
    public String getFirstName() {
        return "------";
    }
    public String getLastName() {
        return "------";
    }
    public void setHcpName(String hcpName) {
        this.hcpName = hcpName;
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

    void load(){
        Dictionary<Object, Object> currentUser = hcpMapper.findUserByEmail(super.email);
        if (this.hcpName == null){
            this.hcpName = (String) currentUser.get("hcpname");
        }
        if (this.identity == null){
            this.identity = (String) currentUser.get("user_identity");
        }
        if (this.postcode == null){
            this.postcode = (String) currentUser.get("postcode");
        }
        if (this.typeofprovider == null){
            this.typeofprovider = (String) currentUser.get("typeofprovider");
        }
    }
}
