package com.example.swen90008sda8.UnitOfWork;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Mappers.TimeSlotMapper;
import com.example.swen90008sda8.Mappers.UserMapper;

import java.sql.SQLException;

public class UnitOfWork {
    public static Boolean bookTimeSlot (String email,String date,String from,String to, String provider) throws SQLException {
        Boolean result = false;
        Integer oldSlotId = UserMapper.getBookingIdByEmail(email);
        Integer oldNumberOfShots = TimeSlotMapper.getNumberOfShotsById(oldSlotId);
        Integer newSlotId = TimeSlotMapper.getIdByDetails(date,from,to,provider);
        Integer newNumberOfShots = TimeSlotMapper.getNumberOfShotsByDetails(date,from,to,provider);
        if(oldSlotId == 0){
            if(newNumberOfShots>0){
                result = true;
                UserMapper.updateBookingByEmail(newSlotId,email);
                TimeSlotMapper.updateNumberOfShotsByID(newSlotId,newNumberOfShots,-1);
            }else{
                return false;
            }
        }else if (oldSlotId == newSlotId){
            return false;
        }else {
            if(newNumberOfShots>0){
                result = true;
                UserMapper.updateBookingByEmail(newSlotId,email);
                TimeSlotMapper.updateNumberOfShotsByID(oldSlotId,oldNumberOfShots,1);
                TimeSlotMapper.updateNumberOfShotsByID(newSlotId,newNumberOfShots,-1);
            }else{
                return false;
            }
        }
        return result;
    }
    public static Boolean updateTimeSlot (String email,String date,String from,String to, String provider) throws SQLException {
        Boolean result = false;
        Integer slotId = UserMapper.getBookingIdByEmail(email);
        Integer numberOfShots = TimeSlotMapper.getNumberOfShotsById(slotId);
        String stmt = "UPDATE  timeslots  SET numberofshots =" + (numberOfShots + 1) + "WHERE id =" + slotId + ";";
        new postgresqlConnector().connect(stmt);
        result = bookTimeSlot(email,date,from,to,provider);
        return result;
    }
}
