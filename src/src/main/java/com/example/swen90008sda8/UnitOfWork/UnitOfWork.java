package com.example.swen90008sda8.UnitOfWork;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Mappers.TimeSlotMapper;
import com.example.swen90008sda8.Mappers.UserMapper;

import java.sql.SQLException;

public class UnitOfWork {
    public static Boolean bookTimeSlot (String email,String date,String from,String to, String provider) throws SQLException {
        Boolean result = false;
        Integer oldSlotId = UserMapper.getBookingIdByEmail(email);
        Integer oldNumberOfShots = 0;
        Integer newSlotId = TimeSlotMapper.getIdByDetails(date,from,to,provider);
        Integer newNumberOfShots = 0;
        if(oldSlotId == 0){
            newNumberOfShots = TimeSlotMapper.getNumberOfShotsByDetails(date,from,to,provider);
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
            newNumberOfShots = TimeSlotMapper.getNumberOfShotsByDetails(date,from,to,provider);
            oldNumberOfShots = TimeSlotMapper.getNumberOfShotsById(oldSlotId);
            if(newNumberOfShots>0){
                result = true;
                UserMapper.updateBookingByEmail(newSlotId,email);
                TimeSlotMapper.updateNumberOfShotsByID(oldSlotId,oldNumberOfShots,1);
                TimeSlotMapper.updateNumberOfShotsByID(newSlotId,newNumberOfShots,-1);
            }else{
                return false;
            }
        }
        System.out.println("Why not\n");
        return result;
    }
}
