package org.unimelb.cis.swen90007sda8.Models;

public class vaccineModel {
    private String name;
    private String fromAge;
    private String toAge;

    public vaccineModel(String name, String fromAge, String toAge) {
        this.name = name;
        this.fromAge = fromAge;
        this.toAge = toAge;
    }

    public vaccineModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromAge() {
        return fromAge;
    }

    public void setFromAge(String fromAge) {
        this.fromAge = fromAge;
    }

    public String getToAge() {
        return toAge;
    }

    public void setToAge(String toAge) {
        this.toAge = toAge;
    }
}
