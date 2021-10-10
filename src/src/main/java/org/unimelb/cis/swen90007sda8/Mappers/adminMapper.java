package org.unimelb.cis.swen90007sda8.Mappers;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
import org.unimelb.cis.swen90007sda8.Models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class adminMapper implements UserInterface {
    public static userModel findUser(String email, String password){
        String stmt = "SELECT email,password,user_identity,hcpname FROM users where email =" + "'" +email + "'" +
                " AND password = "+ "'" +password+ "';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        userModel user = null;
        try {
            if (!rs.next()) {
                return null;
            } else {
                if (rs.getString(3)=="Health Care Provider"){
                    user = new hcpModel(email);
                }
                else if(rs.getString(3)=="Admin"){
                    user = new adminModel(email);
                }else{
                    user = new recipientModel(email);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static userModel find(String email){
        String stmt = "SELECT email,password,user_identity,hcpname FROM users where email =" + "'" +email + "';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        userModel user = null;
        try {
            if (!rs.next()) {
                return null;
            } else {
                if (rs.getString(3)=="Health Care Provider"){
                    user = new hcpModel(email);
                }
                else if(rs.getString(3)=="Admin"){
                    user = new adminModel(email);
                }else{
                    user = new recipientModel(email);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String isUserExisted(String email) throws SQLException {
        String stmt = "SELECT email From users WHERE email ="+"'"+email+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        if(rs.next()){
            return rs.getString(1);
        }else{
            return null;
        }
    }
    public static List<userModel> findWithVaccineName (vaccineModel vaccineName){
        List<userModel> result = new ArrayList<>();
        String stmt = "SELECT users.email FROM users LEFT JOIN bookings ON users.email = bookings.email WHERE vaccinename = '" + vaccineName.getName()+ "' AND users.email != 'admin@gmail.com';";
        return getUserModels(result, stmt);
    }

    private static List<userModel> getUserModels(List<userModel> result, String stmt) {
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                userModel user = new recipientModel(rs.getString("email"));
                result.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static List<userModel> findWithVaccinated (String vaccinated,String identity) {
        List<userModel> result = new ArrayList<>();
        String stmt;
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
                stmt = "SELECT users.email, user_identity, vaccinated From bookings Left JOIN users on " +
                        "bookings.email=users.email WHERE users.user_identity = 'Recipient' AND " +
                        "users.email != 'admin@gmail.com' ORDER BY users.email ASC;";
            }else if(vaccinated.equals("True")){
                stmt = "SELECT users.email, user_identity, vaccinated From bookings Left JOIN users on " +
                        "bookings.email=users.email WHERE users.user_identity = 'Recipient' AND " +
                        "users.email != 'admin@gmail.com' AND vaccinated = True ORDER BY users.email ASC;";

            }else{
                stmt = "SELECT users.email, user_identity, vaccinated From bookings Left JOIN users on " +
                        "bookings.email=users.email WHERE users.user_identity = 'Recipient' AND " +
                        "users.email != 'admin@gmail.com' AND vaccinated = False ORDER BY users.email ASC;";
            }
        }else{
            return result;
        }
        return getUserModels(result, stmt);
    }
    public static Boolean insertNewProvider(String user, String pass,String identity, String post, String top, String hcpname) throws SQLException {
        String s = "INSERT INTO users(email, password, user_identity, postcode, hcpname," +
                " typeofprovider) VALUES (" +"'"+user+"'"+','+"'"+pass+"'"+','+"'"+identity+"'"+','+"'"+post+"'"+','+"'"+hcpname+"'"+','+"'"+top+"'"+ ");";
        if(isUserExisted(user)==null){
            new postgresqlConnector().connect(s);
            return true;
        }else{
            return false;
        }
    }
    public static Boolean insertNewRecipient(String user, String pass, String date, String firstName, String lastName, String identity) throws SQLException {
        String s = "INSERT INTO users(email, password, dateofbirth, firstname, lastname, user_identity) VALUES (" +"'"+user+"'"+','+"'"+pass+"'"+','+"'"+date+"'"+','+"'"+ firstName+"'"+','
                +"'"+lastName+"'"+','+"'"+identity+"'"+ ");";
        if(isUserExisted(user)==null){
            new postgresqlConnector().connect(s);
            return true;
        }else{
            return false;
        }
    }
    public static Dictionary<Object, Object> findUserByEmail(String email){
        String stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, hcpname FROM users " +
                "WHERE email = '"+email+"';";
        ResultSet rs = new postgresqlConnector().connect(stmt);
        Dictionary<Object, Object> user = new Hashtable<>();
        try {
            while (rs.next()) {
                user.put("firstname", rs.getString("firstname"));
                user.put("lastname", rs.getString("lastname"));
                user.put("dateofbirth", rs.getDate("dateofbirth"));
                user.put("user_identity", rs.getString("user_identity"));
                user.put("vaccinated", rs.getBoolean("vaccinated"));
                user.put("postcode", rs.getString("postcode"));
                user.put("typeofprovider", rs.getString("typeofprovider"));
                if(rs.getString("hcpname")!=null){
                    user.put("hcpname", rs.getString("hcpname"));
                }else{
                    user.put("hcpname", "----");
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
