package org.unimelb.cis.swen90007sda8.Models;

public class bookingModel {
    private String userId;
    private Integer timeSlotId;
    private String vaccineName;

    public bookingModel(String userId, Integer timeSlotId, String vaccineName) {
        this.userId = userId;
        this.timeSlotId = timeSlotId;
        this.vaccineName = vaccineName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
}
