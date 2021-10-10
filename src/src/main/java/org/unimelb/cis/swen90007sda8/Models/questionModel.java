package org.unimelb.cis.swen90007sda8.Models;

public class questionModel {
    private Integer id;
    private vaccineModel vaccine;
    private String questions;
    private Boolean desiredAnswer;



    public questionModel(Integer id, vaccineModel vaccine, String questions, Boolean desiredAnswer) {
        this.id = id;
        this.vaccine = vaccine;
        this.questions = questions;
        this.desiredAnswer = desiredAnswer;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public vaccineModel getVaccine() {
        return vaccine;
    }

    public void setVaccine(vaccineModel vaccine) {
        this.vaccine = vaccine;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Boolean getDesiredAnswer() {
        return desiredAnswer;
    }

    public void setDesiredAnswer(Boolean desiredAnswer) {
        this.desiredAnswer = desiredAnswer;
    }
}
