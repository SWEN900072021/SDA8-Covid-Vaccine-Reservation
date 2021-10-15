package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Models.bookingModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper {
    public static void insert(bookingModel booking) {
            int oldBooking;
            String stmt;

            stmt = "SELECT timeslotid FROM bookings WHERE email ='"+ booking.getUser().getEmail()+"';";
            ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
            try{
                if(rs.next()){
                    oldBooking = rs.getInt("timeslotid");
                }
                else{
                    oldBooking = 0;
                }
            }catch (SQLException e){
                e.printStackTrace();
                return;
            }
            Integer targetNumberOfShots = TimeSlotMapper.find(booking.getTimeSlot().getId()).getNumberofshots();
            if(targetNumberOfShots>0){
                if(oldBooking==0){
                    stmt = "UPDATE timeslots SET numberofshots = numberofshots -1 WHERE id ="+ booking.getTimeSlot().getId()+" AND numberofshots>0;";
                    if(!postgresqlConnector.getInstance().connectBoolean(stmt)){
                        stmt = "INSERT INTO bookings(email, timeslotid, vaccinename) VALUES (" +"'"+booking.getUser().getEmail()+"'"+','+booking.getTimeSlot().getId()+','+"'"+booking.getTimeSlot().getVaccine().getName()+"'"+");";
                        postgresqlConnector.getInstance().connect(stmt);
                    }
                }else{
                    stmt = "UPDATE timeslots SET numberofshots = numberofshots -1 WHERE id ="+ booking.getTimeSlot().getId()+" AND numberofshots>0;";
                    if(!postgresqlConnector.getInstance().connectBoolean(stmt)){
                        stmt = "UPDATE timeslots SET numberofshots = numberofshots + 1 WHERE id ="+ oldBooking+";";
                        postgresqlConnector.getInstance().connect(stmt);
                        stmt = "DELETE FROM bookings WHERE timeslotid ="+oldBooking+" AND email='"+booking.getUser().getEmail()+"';";
                        postgresqlConnector.getInstance().connect(stmt);
                        stmt = "INSERT INTO bookings(email, timeslotid, vaccinename) VALUES (" +"'"+booking.getUser().getEmail()+"'"+','+booking.getTimeSlot().getId()+','+"'"+booking.getTimeSlot().getVaccine().getName()+"'"+");";
                        postgresqlConnector.getInstance().connect(stmt);
                    }
                }
            }
    }
}
