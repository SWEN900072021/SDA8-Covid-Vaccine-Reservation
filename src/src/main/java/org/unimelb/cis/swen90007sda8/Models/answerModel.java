package org.unimelb.cis.swen90007sda8.Models;

public class answerModel {
    private userModel user;
    private questionModel question;
    private Boolean answer;

    public answerModel(userModel user) {
        this.user = user;
    }
    public answerModel(userModel user, questionModel question, Boolean answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }
    public userModel getUser() {
        return user;
    }

    public void setUser(userModel user) {
        this.user = user;
    }

    public questionModel getQuestion() {
        return question;
    }

    public void setQuestion(questionModel question) {
        this.question = question;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
