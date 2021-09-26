package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.bookingModel;

public class BookingMapper {
    public static void insert(bookingModel booking) {
            String stmt = "INSERT INTO bookings(email, timeslotid, vaccinename) VALUES (" +"'"+booking.getUserId()+"'"+','+booking.getTimeSlotId()+','+"'"+booking.getVaccineName()+"'"+");";
            new postgresqlConnector().connect(stmt);
    }
}
