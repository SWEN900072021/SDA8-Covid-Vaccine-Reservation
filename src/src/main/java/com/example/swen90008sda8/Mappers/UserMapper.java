package com.example.swen90008sda8.Mappers;

import com.example.swen90008sda8.DBConnector.postgresqlConnector;
import com.example.swen90008sda8.Models.userModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
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
    public static List<userModel> findWithVaccinated (String vaccinated,String identity) {
        List<userModel> result = new ArrayList<>();
        String stmt = "";
        if(identity.equals("Admin")){
            if (vaccinated == null){
                stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE email != 'admin@gmail.com';";
            }else if(vaccinated.equals("True")){
                stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE vaccinated = True AND email != 'admin@gmail.com';";
            }else{
                stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE vaccinated = False AND email != 'admin@gmail.com';";
            }
        }else if(identity.equals("Health Care Provider")){
            if (vaccinated == null){
                stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE user_identity = 'Recipient' AND email != 'admin@gmail.com';";
            }else if(vaccinated.equals("True")){
                stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE user_identity = 'Recipient' AND vaccinated = True;";
            }else{
                stmt = "SELECT email, dateofbirth, firstname, lastname, user_identity, postcode, typeofprovider, vaccinated, bookedtimeslot FROM users " +
                        "WHERE user_identity = 'Recipient' AND vaccinated = False;";
            }
        }else{
            return result;
        }
        ResultSet rs = new postgresqlConnector().connect(stmt);
        try {
            while (rs.next()) {
                userModel user = new userModel();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDate(rs.getDate("dateofbirth"));
                user.setIdentity(rs.getString("user_identity"));
                user.setVaccinated(rs.getBoolean("vaccinated"));
                user.setTimeslotID(rs.getInt("bookedtimeslot"));
                result.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
