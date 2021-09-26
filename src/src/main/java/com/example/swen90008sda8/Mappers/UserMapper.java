package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.userModel;
import org.postgresql.util.PSQLException;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class UserMapper {
    public static Boolean getVaccinatedByEmail(String email){
        Boolean result = null;
        String s = "SELECT vaccinated FROM users WHERE email = '" + email + "';";
        ResultSet rs = new postgresqlConnector().connect(s);
        try {
            if (rs.next()) {
                result = rs.getBoolean(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static void setVaccinatedByEmail(String email){
        String s = "UPDATE  users  SET vaccinated = True WHERE email ="+"'"+ email+"';";
        new postgresqlConnector().connect(s);
    }
    public static void setNotVaccinatedByEmail(String email){
        String s = "UPDATE  users  SET vaccinated = False WHERE email ="+"'"+ email+"';";
        new postgresqlConnector().connect(s);
    }
    public static userModel findUser(String email, String password){
        String stmt = "SELECT email,password,user_identity,hcpname FROM users where email =" + "'" +email + "'" +
                " AND password = "+ "'" +password+ "';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        userModel user = new userModel();
        try {
            if (rs.next() == false) {
                return null;
            } else {
                user.setEmail(email);
                user.setIdentity(rs.getString(3));
                user.setHcpName(rs.getString(4));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void deleteBookingById(Integer slotId){
        String stmt = "UPDATE  users  SET bookedtimeslot = 0 WHERE bookedtimeslot =" + slotId + ";";
        new postgresqlConnector().connect(stmt);
    }
    public static void updateBookingByEmail(Integer slotId, String email){
        String stmt = "UPDATE  users  SET bookedtimeslot =" + slotId + "WHERE email =" + "'" + email + "';";
        new postgresqlConnector().connect(stmt);
    }
    public static Integer getBookingIdByEmail(String email) throws SQLException {
        String stmt = "SELECT bookedtimeslot From users WHERE email ="+"'"+email+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        rs.next();
        Integer id = rs.getInt(1);
        return id;
    }
    public static String isUserExisted(String email) throws SQLException {
        String stmt = "SELECT email From users WHERE email ="+"'"+email+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        if(rs.next()){
            String result = rs.getString(1);
            return result;
        }else{
            return null;
        }
    }
    public static List<userModel> findWithVaccineName (String vaccineName){
        List<userModel> result = new ArrayList<>();
        String stmt = "SELECT email From\n" +
                "(SELECT * FROM users LEFT JOIN timeslots ON users.bookedtimeslot = timeslots.id) AS Users WHERE vaccinename = '" + vaccineName+ "' AND email != 'admin@gmail.com';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                userModel user = new userModel();
                user.setEmail(rs.getString("email"));
                result.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static List<userModel> findWithVaccinated (String vaccinated,String identity) {
        List<userModel> result = new ArrayList<>();
        String stmt = "";
        if(identity.equals("Admin")){
            if (vaccinated == null){
                stmt = "SELECT email FROM users " +
                        "WHERE email != 'admin@gmail.com';";
            }else if(vaccinated.equals("True")){
                stmt = "SELECT email, vaccinated FROM users " +
                        "WHERE vaccinated = True AND email != 'admin@gmail.com';";
            }else{
                stmt = "SELECT email, vaccinated FROM users " +
                        "WHERE vaccinated = False AND email != 'admin@gmail.com';";
            }
        }else if(identity.equals("Health Care Provider")){
            if (vaccinated == null){
                stmt = "SELECT email, user_identity, vaccinated FROM users " +
                        "WHERE user_identity = 'Recipient' AND email != 'admin@gmail.com' ORDER BY email ASC;";
            }else if(vaccinated.equals("True")){
                stmt = "SELECT email, user_identity, vaccinated FROM users " +
                        "WHERE user_identity = 'Recipient' AND vaccinated = True AND email != 'admin@gmail.com' ORDER BY email ASC;";
            }else{
                stmt = "SELECT email, user_identity, vaccinated FROM users " +
                        "WHERE user_identity = 'Recipient' AND vaccinated = False AND email != 'admin@gmail.com' ORDER BY email ASC;";
            }
        }else{
            return result;
        }
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                userModel user = new userModel();
                user.setEmail(rs.getString("email"));
                result.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static Boolean insertNewProvider(String user, String pass,String identity, String post, String top, String hcpname) throws SQLException {
        String s = "INSERT INTO users(email, password, user_identity, postcode, hcpname," +
                " typeofprovider) VALUES (" +"'"+user+"'"+','+"'"+pass+"'"+','+"'"+identity+"'"+','+"'"+post+"'"+','+"'"+hcpname+"'"+','+"'"+top+"'"+ ");";
        if(isUserExisted(user)==null){
            ResultSet rs = new postgresqlConnector().connect(s);
            return true;
        }else{
            return false;
        }
    }
    public static Boolean insertNewRecipient(String user, String pass, String date, String firstName, String lastName, String identity) throws SQLException {
        String s = "INSERT INTO users(email, password, dateofbirth, firstname, lastname, user_identity) VALUES (" +"'"+user+"'"+','+"'"+pass+"'"+','+"'"+date+"'"+','+"'"+ firstName+"'"+','
                +"'"+lastName+"'"+','+"'"+identity+"'"+ ");";
        if(isUserExisted(user)==null){
            ResultSet rs = new postgresqlConnector().connect(s);
            return true;
        }else{
            return false;
        }
    }
    public static Dictionary findUserByEmail(String email){
        String stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot, hcpname FROM users " +
                "WHERE email = '"+email+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        Dictionary user = new Hashtable();
        try {
            while (rs.next()) {
                user.put("firstname", rs.getString("firstname"));
                user.put("lastname", rs.getString("lastname"));
                user.put("dateofbirth", rs.getDate("dateofbirth"));
                user.put("user_identity", rs.getString("user_identity"));
                user.put("vaccinated", rs.getBoolean("vaccinated"));
                user.put("bookedtimeslot", rs.getInt("bookedtimeslot"));
                user.put("postcode", rs.getString("postcode"));
                user.put("typeofprovider", rs.getString("typeofprovider"));
                user.put("hcpname", rs.getString("hcpname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
