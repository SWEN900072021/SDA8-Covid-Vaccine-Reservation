package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.timeSlotModel;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class TimeSlotMapper {
    public static Boolean insertTimeSlot (String date, String from, String to, String provider, String numberofshots,
                                       String vaccinename) {
        Boolean result = true;
        LocalTime fromTime = LocalTime.parse(from);
        LocalTime toTime = LocalTime.parse(to);
        if(toTime.compareTo(fromTime)>0){
            String stmt = "INSERT INTO timeslots(date, fromTime, toTime, provider, numberofshots, vaccineName) VALUES (" +"'"+date+"'"+','+"'"+from+"'"
                    +','+"'"+to+"'"+','+"'"+provider+"', "+numberofshots+", '"+vaccinename+ "');";
            new postgresqlConnector().connect(stmt);
        }else{
            result = false;
        }
        return result;
    }
    public static void updateNumberOfShotsByID (Integer slotId, Integer numberOfShots, Integer offset){
        String stmt = "UPDATE  timeslots  SET numberofshots =" + (numberOfShots + offset) + "WHERE id =" + slotId + ";";
        new postgresqlConnector().connect(stmt);
    }
    public static Integer getNumberOfShotsById(Integer id) throws SQLException {
        if(id == 0){
            return 0;
        }
        String stmt = "SELECT numberofshots From timeslots WHERE id =" + id + ";";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        rs.next();
        Integer numberOfShots = rs.getInt(1);
        return numberOfShots;
    }
    public static Integer getIdByDetails(String date, String from, String to, String provider) throws SQLException {
        String stmt = "SELECT id FROM timeslots where date =" + "'"+date+"' And"+ " fromtime = "+ "'" +from+ "' And"+ " totime = '"+to
                + "' And"+ " provider = '"+provider+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        rs.next();
        Integer id = rs.getInt(1);
        return id;
    }
    public static Integer getNumberOfShotsByDetails(String date, String from, String to, String provider) throws SQLException {
        String stmt = "SELECT numberofshots FROM timeslots where date =" + "'"+date+"' And"+ " fromtime = "+ "'" +from+ "' And"+ " totime = '"+to
                + "' And"+ " provider = '"+provider+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        rs.next();
        Integer numberofshots = rs.getInt(1);
        return numberofshots;
    }
    public static void deleteTimeSlotByDetails(String date, String from, String to, String provider) throws SQLException {
        Integer slotId = getIdByDetails(date, from, to, provider);
        String stmt = "Delete FROM timeslots where date =" + "'"+date+"' And"+ " fromtime = "+ "'" +from+ "' And"+ " totime = '"+to
                + "' And"+ " provider = '"+provider+"';";
        new postgresqlConnector().connect(stmt);
        UserMapper.deleteBookingById(slotId);
    }
    public static List<timeSlotModel> getTimeSlots(){
        List<timeSlotModel> timeslots = new ArrayList<>();
        String stmt = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots;";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setDate(rs.getDate("date"));
                timeSlot.setFrom(rs.getTime("fromtime"));
                timeSlot.setTo(rs.getTime("totime"));
                timeSlot.setProvider(rs.getString("provider"));
                timeSlot.setNumberofshots(rs.getInt("numberofshots"));
                timeslots.add(timeSlot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timeslots;
    }

    public static List<timeSlotModel> getTimeSlotsByDetails(String identity,String hcpname){
        String stmt = "";
        try {
            if (identity.equals("Recipient")) {
                return null;
            }
            else if(identity.equals("Health Care Provider")){
                stmt = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots"+" WHERE provider="
                        +"'"+hcpname+"';";
            }
            else{
                stmt = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots;";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        List<timeSlotModel> timeslots = new ArrayList<>();
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setDate(rs.getDate("date"));
                timeSlot.setFrom(rs.getTime("fromtime"));
                timeSlot.setTo(rs.getTime("totime"));
                timeSlot.setProvider(rs.getString("provider"));
                timeSlot.setNumberofshots(rs.getInt("numberofshots"));
                timeslots.add(timeSlot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timeslots;
    }
}
