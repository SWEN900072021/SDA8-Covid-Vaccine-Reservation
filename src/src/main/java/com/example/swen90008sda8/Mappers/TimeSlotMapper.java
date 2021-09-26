package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.timeSlotModel;

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

    public static void delete (timeSlotModel timeslot){
    }
    public static Integer getIdByDetails(String date, String from, String to, String provider) throws SQLException {
        String stmt = "SELECT id FROM timeslots where date =" + "'"+date+"' And"+ " fromtime = "+ "'" +from+ "' And"+ " totime = '"+to
                + "' And"+ " provider = '"+provider+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        rs.next();
        Integer id = rs.getInt(1);
        return id;
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
        String stmt = "SELECT id, date, fromTime, toTime, provider, numberofshots, vaccinename FROM timeslots ORDER BY date ASC;";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setId(rs.getInt("id"));
                timeSlot.setDate(rs.getDate("date"));
                timeSlot.setFrom(rs.getTime("fromtime"));
                timeSlot.setTo(rs.getTime("totime"));
                timeSlot.setProvider(rs.getString("provider"));
                timeSlot.setNumberofshots(rs.getInt("numberofshots"));
                timeSlot.setVaccineName(rs.getString("vaccinename"));
                timeslots.add(timeSlot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timeslots;
    }
    public static List<timeSlotModel> getTimeSlotByPostCode(String postcode){
        String stmt = "SELECT date, fromTime, toTime, provider, numberofshots From (SELECT * FROM timeslots " +
                "LEFT JOIN users ON timeslots.provider = users.hcpname) AS timeslot WHERE postcode = '"+ postcode+"' " +
                "ORDER BY date ASC;";
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
    public static List<timeSlotModel> getTimeSlotByProvider(String provider){
        String stmt = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots WHERE provider = '"+
                provider+"' ORDER BY date ASC;";
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
    public static List<timeSlotModel> getTimeSlotsByDetails(String identity,String hcpname){
        String stmt = "";
        try {
            if (identity.equals("Recipient")) {
                return null;
            }
            else if(identity.equals("Health Care Provider")){
                stmt = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots"+" WHERE provider="
                        +"'"+hcpname+"' ORDER BY date ASC;";
            }
            else{
                stmt = "SELECT date, fromTime, toTime, provider, numberofshots FROM timeslots ORDER BY date ASC;";
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
