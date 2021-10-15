package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.LockManager.lockManager;
import org.unimelb.cis.swen90007sda8.Models.timeSlotModel;
import org.unimelb.cis.swen90007sda8.Models.timeRange;
import org.unimelb.cis.swen90007sda8.Models.vaccineModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import java.util.List;


public class TimeSlotMapper {
    public static Integer isTimeSlotExisted(Integer timeid, String provider,
                                           vaccineModel vaccinename) {
        String stmt = "SELECT id From timeslots WHERE timerange ="+timeid+" AND provider='"+provider+"' AND vaccinename='"+vaccinename.getName()+"';";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        try{
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Boolean insertTimeSlot (Integer timeid, String provider, String numberofshots,
                                       vaccineModel vaccinename) {
        if(isTimeSlotExisted(timeid,provider,vaccinename)==null){
            String stmt = "INSERT INTO timeslots(timerange, provider, numberofshots, vaccineName) VALUES (" +timeid+','+"'"+provider+"', "+numberofshots+", '"+vaccinename.getName()+ "');";
            postgresqlConnector.getInstance().connect(stmt);
            return true;
        }else{
            return false;
        }
    }
    public static timeSlotModel find (Integer id) {
        String stmt = "SELECT id, date, fromTime, toTime, provider, numberofshots, vaccinename, toage, fromage From (SELECT * FROM timeslots " +
                "LEFT JOIN users ON timeslots.provider = users.hcpname " +
                "LEFT JOIN vaccines ON timeslots.vaccinename = vaccines.name " +
                "LEFT JOIN timerange ON timeslots.timerange = timerange.timeid) AS timeslot WHERE id='"+id+"' " +
                "ORDER BY date ASC;";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        timeSlotModel timeSlot = new timeSlotModel();
        try{
            while (rs.next()) {
                timeSlot.setId(id);

                Date date = rs.getDate("date");
                Time from = rs.getTime("fromtime");
                Time to = rs.getTime("totime");
                timeSlot.setTimeRange(new timeRange(date,from,to));

                timeSlot.setProvider(rs.getString("provider"));

                timeSlot.setNumberofshots(rs.getInt("numberofshots"));

                String name = rs.getString("vaccinename");
                String toage = rs.getString("toage");
                String fromage = rs.getString("fromage");
                timeSlot.setVaccine(new vaccineModel(name,fromage,toage));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timeSlot;
    }

    public static Integer getIdByDetails(Integer timeid, String provider) throws SQLException {
        String stmt = "SELECT id FROM timeslots where timerange =" + timeid + " And"+ " provider = '"+provider+"';";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        rs.next();
        return rs.getInt(1);
    }
    public static void deleteTimeSlotByDetails(Integer timeid, String provider) throws SQLException {
        Integer slotId = getIdByDetails(timeid, provider);
        String stmt = "Delete FROM bookings where timeslotid =" + slotId+";";
        postgresqlConnector.getInstance().connect(stmt);
        stmt = "Delete FROM timeslots where id =" + slotId+";";
        postgresqlConnector.getInstance().connect(stmt);
    }
    public static List<timeSlotModel> getTimeSlots(){
        List<timeSlotModel> timeslots = new ArrayList<>();
        String stmt = "SELECT id, date, fromTime, toTime, provider, numberofshots, vaccinename From (SELECT * FROM timeslots " +
                "LEFT JOIN timerange ON timeslots.timerange = timerange.timeid) AS timeslot ORDER BY date, vaccinename ASC;";
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setId(rs.getInt("id"));

                Date date = rs.getDate("date");
                Time from = rs.getTime("fromtime");
                Time to = rs.getTime("totime");
                timeSlot.setTimeRange(new timeRange(date,from,to));

                timeSlot.setProvider(rs.getString("provider"));

                timeSlot.setNumberofshots(rs.getInt("numberofshots"));

                vaccineModel vaccine = VaccineMapper.find(rs.getString("vaccinename"));
                timeSlot.setVaccine(vaccine);
                timeslots.add(timeSlot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timeslots;
    }
    public static List<timeSlotModel> getTimeSlotByPostCode(String postcode){
        String stmt = "SELECT id, date, fromTime, toTime, provider, numberofshots, vaccinename From (SELECT * FROM timeslots " +
                "LEFT JOIN users ON timeslots.provider = users.hcpname " +
                "LEFT JOIN timerange ON timeslots.timerange = timerange.timeid) AS timeslot WHERE postcode = '"+ postcode+"' " +
                "ORDER BY date, vaccinename ASC;";
        List<timeSlotModel> timeslots = new ArrayList<>();
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setId(rs.getInt("id"));
                Date date = rs.getDate("date");
                Time from = rs.getTime("fromtime");
                Time to = rs.getTime("totime");
                timeSlot.setTimeRange(new timeRange(date,from,to));
                timeSlot.setProvider(rs.getString("provider"));
                timeSlot.setNumberofshots(rs.getInt("numberofshots"));
                vaccineModel vaccine = VaccineMapper.find(rs.getString("vaccinename"));
                timeSlot.setVaccine(vaccine);
                timeslots.add(timeSlot);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return timeslots;
    }
    public static List<timeSlotModel> getTimeSlotByProvider(String provider){
        String stmt = "SELECT id, date, fromTime, toTime, provider, numberofshots, vaccinename FROM (SELECT * FROM timeslots " +
                "LEFT JOIN users ON timeslots.provider = users.hcpname " +
                "LEFT JOIN timerange ON timeslots.timerange = timerange.timeid"+
                ") AS timeslot WHERE provider = '"+ provider+"' ORDER BY date, vaccinename ASC;";
        List<timeSlotModel> timeslots = new ArrayList<>();
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                timeSlot.setId(rs.getInt("id"));
                Date date = rs.getDate("date");
                Time from = rs.getTime("fromtime");
                Time to = rs.getTime("totime");
                timeSlot.setTimeRange(new timeRange(date,from,to));
                timeSlot.setProvider(rs.getString("provider"));
                timeSlot.setNumberofshots(rs.getInt("numberofshots"));
                vaccineModel vaccine = VaccineMapper.find(rs.getString("vaccinename"));
                timeSlot.setVaccine(vaccine);
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
                stmt = "SELECT date, fromTime, toTime, provider, numberofshots From (SELECT * FROM timeslots " +
                        "LEFT JOIN timerange ON timeslots.timerange = timerange.timeid) AS timeslot WHERE provider="
                        +"'"+hcpname+"' ORDER BY date, vaccinename ASC;";

            }
            else{
                stmt = "SELECT date, fromTime, toTime, provider, numberofshots From (SELECT * FROM timeslots " +
                        "LEFT JOIN timerange ON timeslots.timerange = timerange.timeid) AS timeslot ORDER BY date, vaccinename ASC;";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        List<timeSlotModel> timeslots = new ArrayList<>();
        ResultSet rs = postgresqlConnector.getInstance().connect(stmt);
        try{
            while (rs.next()) {
                timeSlotModel timeSlot = new timeSlotModel();
                Date date = rs.getDate("date");
                Time from = rs.getTime("fromtime");
                Time to = rs.getTime("totime");
                timeSlot.setTimeRange(new timeRange(date,from,to));
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
