package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.Models.bookingModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper {
    public static void insert(bookingModel booking) {
            postgresqlConnector conn = new postgresqlConnector();
            int oldBooking;
            String stmt;
            stmt = "SELECT timeslotid FROM bookings WHERE email ='"+ booking.getUserId()+"';";
            ResultSet rs = conn.connect(stmt);

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
            if(oldBooking==0){
                stmt = "UPDATE timeslots SET numberofshots = numberofshots -1 WHERE id ="+ booking.getTimeSlotId()+" AND numberofshots>1;";
            }else{stmt = "UPDATE timeslots SET numberofshots = numberofshots + 1 WHERE id ="+ oldBooking+";";
                conn.connect(stmt);
                stmt = "UPDATE timeslots SET numberofshots = numberofshots -1 WHERE id ="+ booking.getTimeSlotId()+" AND numberofshots>1;";
                conn.connect(stmt);
                stmt = "DELETE FROM bookings WHERE timeslotid ="+oldBooking+" AND email='"+booking.getUserId()+"';";
            }
        conn.connect(stmt);
        stmt = "INSERT INTO bookings(email, timeslotid, vaccinename) VALUES (" +"'"+booking.getUserId()+"'"+','+booking.getTimeSlotId()+','+"'"+booking.getVaccineName()+"'"+");";
            conn.connect(stmt);
    }
}
