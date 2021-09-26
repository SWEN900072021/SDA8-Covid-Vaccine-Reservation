package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.bookingModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper {
    public static void insert(bookingModel booking) {
            postgresqlConnector conn = new postgresqlConnector();
            Integer oldBooking;
            String stmt;
            stmt = "SELECT bookedtimeslot FROM users WHERE email ='"+ booking.getUserId()+"';";
            ResultSet rs = conn.connect(stmt);
            try{
                rs.next();
                oldBooking = rs.getInt("bookedtimeslot");
            }catch (SQLException e){
                e.printStackTrace();
                return;
            }
            if(oldBooking==0){
                stmt = "UPDATE timeslots SET numberofshots = numberofshots -1 WHERE id ="+ booking.getTimeSlotId()+" AND numberofshots>1;";
                conn.connect(stmt);
                stmt = "UPDATE users SET bookedtimeslot ="+booking.getTimeSlotId()+" WHERE email='"+booking.getUserId()+"';";
                conn.connect(stmt);
            }else{stmt = "UPDATE timeslots SET numberofshots = numberofshots + 1 WHERE id ="+ oldBooking+";";
                conn.connect(stmt);
                stmt = "UPDATE timeslots SET numberofshots = numberofshots -1 WHERE id ="+ booking.getTimeSlotId()+" AND numberofshots>1;";
                conn.connect(stmt);
                stmt = "UPDATE users SET bookedtimeslot ="+booking.getTimeSlotId()+" WHERE email='"+booking.getUserId()+"';";
                conn.connect(stmt);
                stmt = "DELETE FROM bookings WHERE timeslotid ="+oldBooking+" AND email='"+booking.getUserId()+"';";
                conn.connect(stmt);
            }
            stmt = "INSERT INTO bookings(email, timeslotid, vaccinename) VALUES (" +"'"+booking.getUserId()+"'"+','+booking.getTimeSlotId()+','+"'"+booking.getVaccineName()+"'"+");";
            conn.connect(stmt);
    }
}