package org.unimelb.cis.swen90007sda8.Models;

public class bookingModel {
    private userModel user;
    private timeSlotModel timeSlot;

    public bookingModel(userModel user, timeSlotModel timeSlotId) {
        this.user = user;
        this.timeSlot = timeSlotId;
    }

    public userModel getUser() {
        return user;
    }

    public void setUser(userModel user) {
        this.user = user;
    }

    public timeSlotModel getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(timeSlotModel timeSlot) {
        this.timeSlot = timeSlot;
    }

}
