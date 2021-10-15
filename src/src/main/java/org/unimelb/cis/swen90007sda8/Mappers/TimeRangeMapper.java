package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class TimeRangeMapper {
    public static Integer insertTimeRange (String date, String from, String to) {
        Integer id = null;
        LocalTime fromTime = LocalTime.parse(from);
        LocalTime toTime = LocalTime.parse(to);
        id = getIdByDetail(date, from, to);
        if(id!=null){
            return id;
        }
        if(toTime.compareTo(fromTime)>0){
            String stmt = "INSERT INTO timerange(date, fromTime, toTime) VALUES (" +"'"+date+"'"+','+"'"+from+"'"
                    +','+"'"+to+ "');";
            postgresqlConnector.getInstance().connect(stmt);
            stmt = "SELECT timeid from timerange WHERE date = "+"'"+date+"'"+" AND"+" fromTime ="+"'"+from+"'"
                    +" AND "+"toTime="+"'"+to+ "';";
            ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
            try {
                rs.next();
                id = rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            return -1;
        }
        return id;
    }

    public  static Integer getIdByDetail(String date, String from, String to) {
        String stmt = "SELECT timeid from timerange WHERE date = "+"'"+date+"'"+" AND"+" fromTime ="+"'"+from+"'"
                +" AND "+"toTime="+"'"+to+ "';";
        Integer id = null;
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        try {
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
