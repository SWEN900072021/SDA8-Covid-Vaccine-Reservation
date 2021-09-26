package com.example.swen90008sda8.UnitOfWork;
import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Mappers.TimeSlotMapper;
import com.example.swen90008sda8.Mappers.UserMapper;

import java.sql.SQLException;

public class UnitOfWork {
    public static Boolean bookTimeSlot (String email,String date,String from,String to, String provider) throws SQLException {

        Integer newSlotId = TimeSlotMapper.getIdByDetails(date,from,to,provider);
        TimeSlotMapper.updateNumberOfShotsByEmail(newSlotId,email);
        return true;
    }
}
